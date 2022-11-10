package com.timsmithsoftware.integration_tests
import com.timsmithsoftware.integration_tests.models.Connection
import com.timsmithsoftware.integration_tests.models.IApiConnection
import com.timsmithsoftware.integration_tests.models.IApiRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TestBuilderTests {

    private val mockApiConnection: IApiConnection = Mockito.mock(IApiConnection::class.java)
    private val mockDbConnection: Connection = Mockito.mock(Connection::class.java)
    private val sut: TestBuilder = TestBuilder(mockApiConnection, mockDbConnection)

    @Test
    fun withRequestCorrectlySetsRequest() {
        val mockRequest = Mockito.mock(IApiRequest::class.java)
        sut.build().withRequest(mockRequest)
        sut.call()
        Mockito.verify(mockApiConnection).call(mockRequest)
    }
}