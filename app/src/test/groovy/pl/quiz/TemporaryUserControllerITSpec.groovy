package pl.quiz

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import pl.quiz.domain.ErrorConstraint
import pl.quiz.domain.dto.FailedValidationDto
import spock.lang.Specification

import java.nio.charset.StandardCharsets

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static spock.util.matcher.HamcrestSupport.that


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles('test-h2')
@ContextConfiguration(classes = [App])
@AutoConfigureMockMvc
@Transactional
class TemporaryUserControllerITSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper


    @Sql([
            'classpath:fixture/sql/common/open_room_with_questions.sql'
    ])
    def 'should return 200 when json creating temp user is ok'() {

        given:
            String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/tempuser/correctNewTempUser.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(post(ControllerMapping.CREATE_TMP_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(staticDataString))

        then:
            result.andExpect(status().isOk())
    }


    def 'should return 400 when json creating temp user have no existing room'() {
        given:
            String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/tempuser/correctNewTempUser.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(post(ControllerMapping.CREATE_TMP_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(staticDataString))
                .andReturn()
                .response

        then:
            result.status == HttpStatus.BAD_REQUEST.value()
            with(objectMapper.readValue(result.contentAsString, FailedValidationDto)) {
                that it.messages, containsInAnyOrder(
                        ErrorConstraint.ROOM_DOES_NOT_EXIST
                )
                it.messages.size() == 1
            }
    }
}
