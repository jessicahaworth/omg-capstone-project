package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(ProjectSkillController)
@Mock(ProjectSkill)
class ProjectSkillControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/projectSkill/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.projectSkillInstanceList.size() == 0
        assert model.projectSkillInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.projectSkillInstance != null
    }

    void testSave() {
        controller.save()

        assert model.projectSkillInstance != null
        assert view == '/projectSkill/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/projectSkill/show/1'
        assert controller.flash.message != null
        assert ProjectSkill.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/projectSkill/list'


        populateValidParams(params)
        def projectSkill = new ProjectSkill(params)

        assert projectSkill.save() != null

        params.id = projectSkill.id

        def model = controller.show()

        assert model.projectSkillInstance == projectSkill
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/projectSkill/list'


        populateValidParams(params)
        def projectSkill = new ProjectSkill(params)

        assert projectSkill.save() != null

        params.id = projectSkill.id

        def model = controller.edit()

        assert model.projectSkillInstance == projectSkill
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/projectSkill/list'

        response.reset()


        populateValidParams(params)
        def projectSkill = new ProjectSkill(params)

        assert projectSkill.save() != null

        // test invalid parameters in update
        params.id = projectSkill.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/projectSkill/edit"
        assert model.projectSkillInstance != null

        projectSkill.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/projectSkill/show/$projectSkill.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        projectSkill.clearErrors()

        populateValidParams(params)
        params.id = projectSkill.id
        params.version = -1
        controller.update()

        assert view == "/projectSkill/edit"
        assert model.projectSkillInstance != null
        assert model.projectSkillInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/projectSkill/list'

        response.reset()

        populateValidParams(params)
        def projectSkill = new ProjectSkill(params)

        assert projectSkill.save() != null
        assert ProjectSkill.count() == 1

        params.id = projectSkill.id

        controller.delete()

        assert ProjectSkill.count() == 0
        assert ProjectSkill.get(projectSkill.id) == null
        assert response.redirectedUrl == '/projectSkill/list'
    }
}
