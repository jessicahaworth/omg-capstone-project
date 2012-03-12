package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(MemberOfProjectController)
@Mock(MemberOfProject)
class MemberOfProjectControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/memberOfProject/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.memberOfProjectInstanceList.size() == 0
        assert model.memberOfProjectInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.memberOfProjectInstance != null
    }

    void testSave() {
        controller.save()

        assert model.memberOfProjectInstance != null
        assert view == '/memberOfProject/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/memberOfProject/show/1'
        assert controller.flash.message != null
        assert MemberOfProject.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/memberOfProject/list'


        populateValidParams(params)
        def memberOfProject = new MemberOfProject(params)

        assert memberOfProject.save() != null

        params.id = memberOfProject.id

        def model = controller.show()

        assert model.memberOfProjectInstance == memberOfProject
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/memberOfProject/list'


        populateValidParams(params)
        def memberOfProject = new MemberOfProject(params)

        assert memberOfProject.save() != null

        params.id = memberOfProject.id

        def model = controller.edit()

        assert model.memberOfProjectInstance == memberOfProject
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/memberOfProject/list'

        response.reset()


        populateValidParams(params)
        def memberOfProject = new MemberOfProject(params)

        assert memberOfProject.save() != null

        // test invalid parameters in update
        params.id = memberOfProject.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/memberOfProject/edit"
        assert model.memberOfProjectInstance != null

        memberOfProject.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/memberOfProject/show/$memberOfProject.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        memberOfProject.clearErrors()

        populateValidParams(params)
        params.id = memberOfProject.id
        params.version = -1
        controller.update()

        assert view == "/memberOfProject/edit"
        assert model.memberOfProjectInstance != null
        assert model.memberOfProjectInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/memberOfProject/list'

        response.reset()

        populateValidParams(params)
        def memberOfProject = new MemberOfProject(params)

        assert memberOfProject.save() != null
        assert MemberOfProject.count() == 1

        params.id = memberOfProject.id

        controller.delete()

        assert MemberOfProject.count() == 0
        assert MemberOfProject.get(memberOfProject.id) == null
        assert response.redirectedUrl == '/memberOfProject/list'
    }
}
