package pl.quiz

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import pl.quiz.domain.dto.vo.TempUserFinishDataVO
import pl.quiz.domain.event.AsyncCloseTempUserListener
import spock.lang.Specification

import java.nio.charset.StandardCharsets

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static spock.util.matcher.HamcrestSupport.that


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles('test-h2')
@ContextConfiguration(classes = [App])
@AutoConfigureMockMvc
@Transactional
class RoomUseCaseControllerITSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    AsyncCloseTempUserListener asyncCloseTempUserListener

    def 'should return 401 with needed redirection message'() {
        when:
        def result = mockMvc.perform(
                post(ControllerMapping.OPEN_ROOM, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
        result.status == HttpStatus.UNAUTHORIZED.value()
    }

    @Sql([
            'classpath:fixture/sql/roomusecase/room_with_questions.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 400 if room still no open'() {
        when:
        def result = mockMvc.perform(
                post(ControllerMapping.OPEN_ROOM, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
        result.status == HttpStatus.BAD_REQUEST.value()
    }

    @Sql([
            'classpath:fixture/sql/common/open_room_with_questions.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 200 if room exists and is open'() {
        when:
        def result = mockMvc.perform(
                post(ControllerMapping.OPEN_ROOM, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
        result.status == HttpStatus.OK.value()
    }

    @Sql([
            'classpath:fixture/sql/common/open_room_with_questions.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 200 getting next question'() {
        when:
        def result = mockMvc.perform(
                get(ControllerMapping.GET_NEXT_QUESTION, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        then:
        result.andExpect(status().isOk())
                .andExpect(jsonPath('$.id').value(2))
    }


    def 'should return 401  getting next question when user not authorized'() {
        when:
        def result = mockMvc.perform(
                get(ControllerMapping.GET_NEXT_QUESTION, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        then:
        result.andExpect(status().isUnauthorized())
    }

    @Sql([
            'classpath:fixture/sql/common/open_room_with_questions.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 200 answering to question'() {

        given:
        String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/questionanswer/newQuestionAnswer.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(
                post(ControllerMapping.SAVE_QUESTION_ANSWER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(staticDataString)
        )
        then:
        result.andExpect(status().isOk())

    }


    def 'should return 401 answering to question when user not authorized'() {
        given:
        String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/questionanswer/newQuestionAnswer.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(
                post(ControllerMapping.SAVE_QUESTION_ANSWER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(staticDataString)
        )
        then:
        result.andExpect(status().isUnauthorized())
    }

    @Sql([
            'classpath:fixture/sql/roomusecase/room_with_questions_and_answers.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 200 getting finish user data'() {
        given:

        when:
        def result = mockMvc.perform(
                get(ControllerMapping.GET_FINISH_DATA_TO_TMP_USER)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
            result.status == HttpStatus.OK.value()
            with(objectMapper.readValue(result.contentAsString, new TypeReference<List<TempUserFinishDataVO>>() {})) {
                that it, containsInAnyOrder(
                        new TempUserFinishDataVO('test question 1', 1, 3),
                        new TempUserFinishDataVO('test question 2', 1, 1),
                        new TempUserFinishDataVO('test question 3', 1, 2)
                )
                it.size() == 3
            }
    }


    def 'should return 401 getting finish user data when user not authorized'() {

        when:
        def result = mockMvc.perform(
                get(ControllerMapping.GET_FINISH_DATA_TO_TMP_USER)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        then:
        result.andExpect(status().isUnauthorized())
    }

}
