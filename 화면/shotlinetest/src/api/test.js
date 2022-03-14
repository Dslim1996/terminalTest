import service from "../service/request.js"

const PRODUCT_API_BASE_URL = "http://localhost:8080";

class TestApi {

    getTest() {
        return service.get(PRODUCT_API_BASE_URL + '/test');
    }

}

export default new TestApi();