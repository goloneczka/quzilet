package pl.quiz.it

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import pl.quiz.App
import pl.quiz.domain.ErrorConstraint
import pl.quiz.domain.dto.FailedValidationDto

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets
import org.apache.commons.io.IOUtils
import static org.hamcrest.Matchers.containsInAnyOrder
import static pl.quiz.ControllerMapping.CREATE_ROOM
import static spock.util.matcher.HamcrestSupport.that

import spock.lang.Specification


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles('test-h2')
@ContextConfiguration(classes = [App])
@AutoConfigureMockMvc
@Transactional
@Sql([
        'classpath:fixture/sql/creatoruser/creator_user.sql'
])
class RoomControllerITSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @WithUserDetails(value = "Im_room_creator", userDetailsServiceBeanName = "creatorUserAuthService")
    def 'should return 200 when json creating room is ok'() {

        given:
            String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/room/correctNewRoom.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(post(CREATE_ROOM)
                .contentType(MediaType.APPLICATION_JSON)
                .content(staticDataString))

        then:
            result.andExpect(status().isOk())

    }

    @WithUserDetails(value = "Im_room_creator", userDetailsServiceBeanName = "creatorUserAuthService")
    def 'should return error when json creating room has wrong answer'() {

        given:
        String staticDataString = IOUtils.toString(new ClassPathResource("fixture/json/room/newRoomWithIncorrectAnswer.json").getInputStream(), StandardCharsets.UTF_8)

        when:
        def result = mockMvc.perform(post(CREATE_ROOM)
                .contentType(MediaType.APPLICATION_JSON)
                .content(staticDataString))
            .andReturn()
            .response

        then:
            result.status == HttpStatus.BAD_REQUEST.value()
            with(objectMapper.readValue(result.contentAsString, FailedValidationDto)) {
                that it.messages, containsInAnyOrder(
                        ErrorConstraint.CORRECT_QUESTION_VALUE
                )
                it.messages.size() == 1
            }
    }
}
