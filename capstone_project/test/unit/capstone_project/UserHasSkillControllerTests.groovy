package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(UserHasSkillController)
@Mock(UserHasSkill)
class UserHasSkillControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userHasSkill/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userHasSkillInstanceList.size() == 0
        assert model.userHasSkillInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.userHasSkillInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userHasSkillInstance != null
        assert view == '/userHasSkill/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userHasSkill/show/1'
        assert controller.flash.message != null
        assert UserHasSkill.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userHasSkill/list'


        populateValidParams(params)
        def userHasSkill = new UserHasSkill(params)

        assert userHasSkill.save() != null

        params.id = userHasSkill.id

        def model = controller.show()

        assert model.userHasSkillInstance == userHasSkill
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userHasSkill/list'


        populateValidParams(params)
        def userHasSkill = new UserHasSkill(params)

        assert userHasSkill.save() != null

        params.id = userHasSkill.id

        def model = controller.edit()

        assert model.userHasSkillInstance == userHasSkill
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userHasSkill/list'

        response.reset()


        populateValidParams(params)
        def userHasSkill = new UserHasSkill(params)

        assert userHasSkill.save() != null

        // test invalid parameters in update
        params.id = userHasSkill.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userHasSkill/edit"
        assert model.userHasSkillInstance != null

        userHasSkill.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userHasSkill/show/$userHasSkill.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userHasSkill.clearErrors()

        populateValidParams(params)
        params.id = userHasSkill.id
        params.version = -1
        controller.update()

        assert view == "/userHasSkill/edit"
        assert model.userHasSkillInstance != null
        assert model.userHasSkillInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userHasSkill/list'

        response.reset()

        populateValidParams(params)
        def userHasSkill = new UserHasSkill(params)

        assert userHasSkill.save() != null
        assert UserHasSkill.count() == 1

        params.id = userHasSkill.id

        controller.delete()

        assert UserHasSkill.count() == 0
        assert UserHasSkill.get(userHasSkill.id) == null
        assert response.redirectedUrl == '/userHasSkill/list'
    }
}
