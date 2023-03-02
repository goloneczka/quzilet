package pl.quiz

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.helpers.MessageFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles('dev-h2')
@ContextConfiguration(classes = [App])
@AutoConfigureMockMvc
@Transactional
class RoomUseCaseControllerITSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

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
    def 'should return 406 if room still no open'() {
        when:
        def result = mockMvc.perform(
                post(ControllerMapping.OPEN_ROOM, 1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
        result.status == HttpStatus.NOT_ACCEPTABLE.value()
    }

    @Sql([
            'classpath:fixture/sql/roomusecase/room_with_questions.sql'
    ])
    @WithUserDetails(value = "1111-222-3333-AA", userDetailsServiceBeanName = "temporaryUserAuthService")
    def 'should return 200 if room exists and is open'() {
        when:
        def result = mockMvc.perform(
                post(ControllerMapping.OPEN_ROOM, 2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()
                .response

        then:
        result.status == HttpStatus.NOT_ACCEPTABLE.value()
    }


}
