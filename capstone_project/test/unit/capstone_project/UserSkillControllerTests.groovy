package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(UserSkillController)
@Mock(UserSkill)
class UserSkillControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userSkill/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userSkillInstanceList.size() == 0
        assert model.userSkillInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.userSkillInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userSkillInstance != null
        assert view == '/userSkill/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userSkill/show/1'
        assert controller.flash.message != null
        assert UserSkill.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userSkill/list'


        populateValidParams(params)
        def userSkill = new UserSkill(params)

        assert userSkill.save() != null

        params.id = userSkill.id

        def model = controller.show()

        assert model.userSkillInstance == userSkill
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userSkill/list'


        populateValidParams(params)
        def userSkill = new UserSkill(params)

        assert userSkill.save() != null

        params.id = userSkill.id

        def model = controller.edit()

        assert model.userSkillInstance == userSkill
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userSkill/list'

        response.reset()


        populateValidParams(params)
        def userSkill = new UserSkill(params)

        assert userSkill.save() != null

        // test invalid parameters in update
        params.id = userSkill.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userSkill/edit"
        assert model.userSkillInstance != null

        userSkill.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userSkill/show/$userSkill.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userSkill.clearErrors()

        populateValidParams(params)
        params.id = userSkill.id
        params.version = -1
        controller.update()

        assert view == "/userSkill/edit"
        assert model.userSkillInstance != null
        assert model.userSkillInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userSkill/list'

        response.reset()

        populateValidParams(params)
        def userSkill = new UserSkill(params)

        assert userSkill.save() != null
        assert UserSkill.count() == 1

        params.id = userSkill.id

        controller.delete()

        assert UserSkill.count() == 0
        assert UserSkill.get(userSkill.id) == null
        assert response.redirectedUrl == '/userSkill/list'
    }
}
