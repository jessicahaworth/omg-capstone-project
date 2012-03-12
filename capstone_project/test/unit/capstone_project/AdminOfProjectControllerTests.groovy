package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(AdminOfProjectController)
@Mock(AdminOfProject)
class AdminOfProjectControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adminOfProject/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adminOfProjectInstanceList.size() == 0
        assert model.adminOfProjectInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.adminOfProjectInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adminOfProjectInstance != null
        assert view == '/adminOfProject/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adminOfProject/show/1'
        assert controller.flash.message != null
        assert AdminOfProject.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adminOfProject/list'


        populateValidParams(params)
        def adminOfProject = new AdminOfProject(params)

        assert adminOfProject.save() != null

        params.id = adminOfProject.id

        def model = controller.show()

        assert model.adminOfProjectInstance == adminOfProject
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adminOfProject/list'


        populateValidParams(params)
        def adminOfProject = new AdminOfProject(params)

        assert adminOfProject.save() != null

        params.id = adminOfProject.id

        def model = controller.edit()

        assert model.adminOfProjectInstance == adminOfProject
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adminOfProject/list'

        response.reset()


        populateValidParams(params)
        def adminOfProject = new AdminOfProject(params)

        assert adminOfProject.save() != null

        // test invalid parameters in update
        params.id = adminOfProject.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adminOfProject/edit"
        assert model.adminOfProjectInstance != null

        adminOfProject.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adminOfProject/show/$adminOfProject.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adminOfProject.clearErrors()

        populateValidParams(params)
        params.id = adminOfProject.id
        params.version = -1
        controller.update()

        assert view == "/adminOfProject/edit"
        assert model.adminOfProjectInstance != null
        assert model.adminOfProjectInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adminOfProject/list'

        response.reset()

        populateValidParams(params)
        def adminOfProject = new AdminOfProject(params)

        assert adminOfProject.save() != null
        assert AdminOfProject.count() == 1

        params.id = adminOfProject.id

        controller.delete()

        assert AdminOfProject.count() == 0
        assert AdminOfProject.get(adminOfProject.id) == null
        assert response.redirectedUrl == '/adminOfProject/list'
    }
}
