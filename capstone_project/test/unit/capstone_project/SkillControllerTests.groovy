package capstone_project



import org.junit.*
import grails.test.mixin.*

@TestFor(SkillController)
@Mock(Skill)
class SkillControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/skill/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.skillInstanceList.size() == 0
        assert model.skillInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.skillInstance != null
    }

    void testSave() {
        controller.save()

        assert model.skillInstance != null
        assert view == '/skill/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/skill/show/1'
        assert controller.flash.message != null
        assert Skill.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/skill/list'


        populateValidParams(params)
        def skill = new Skill(params)

        assert skill.save() != null

        params.id = skill.id

        def model = controller.show()

        assert model.skillInstance == skill
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/skill/list'


        populateValidParams(params)
        def skill = new Skill(params)

        assert skill.save() != null

        params.id = skill.id

        def model = controller.edit()

        assert model.skillInstance == skill
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/skill/list'

        response.reset()


        populateValidParams(params)
        def skill = new Skill(params)

        assert skill.save() != null

        // test invalid parameters in update
        params.id = skill.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/skill/edit"
        assert model.skillInstance != null

        skill.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/skill/show/$skill.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        skill.clearErrors()

        populateValidParams(params)
        params.id = skill.id
        params.version = -1
        controller.update()

        assert view == "/skill/edit"
        assert model.skillInstance != null
        assert model.skillInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/skill/list'

        response.reset()

        populateValidParams(params)
        def skill = new Skill(params)

        assert skill.save() != null
        assert Skill.count() == 1

        params.id = skill.id

        controller.delete()

        assert Skill.count() == 0
        assert Skill.get(skill.id) == null
        assert response.redirectedUrl == '/skill/list'
    }
}
