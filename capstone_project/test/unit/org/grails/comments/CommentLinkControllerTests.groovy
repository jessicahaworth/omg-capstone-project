package org.grails.comments



import org.junit.*
import grails.test.mixin.*

@TestFor(CommentLinkController)
@Mock(CommentLink)
class CommentLinkControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/commentLink/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commentLinkInstanceList.size() == 0
        assert model.commentLinkInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.commentLinkInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commentLinkInstance != null
        assert view == '/commentLink/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/commentLink/show/1'
        assert controller.flash.message != null
        assert CommentLink.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/commentLink/list'


        populateValidParams(params)
        def commentLink = new CommentLink(params)

        assert commentLink.save() != null

        params.id = commentLink.id

        def model = controller.show()

        assert model.commentLinkInstance == commentLink
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/commentLink/list'


        populateValidParams(params)
        def commentLink = new CommentLink(params)

        assert commentLink.save() != null

        params.id = commentLink.id

        def model = controller.edit()

        assert model.commentLinkInstance == commentLink
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/commentLink/list'

        response.reset()


        populateValidParams(params)
        def commentLink = new CommentLink(params)

        assert commentLink.save() != null

        // test invalid parameters in update
        params.id = commentLink.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/commentLink/edit"
        assert model.commentLinkInstance != null

        commentLink.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/commentLink/show/$commentLink.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        commentLink.clearErrors()

        populateValidParams(params)
        params.id = commentLink.id
        params.version = -1
        controller.update()

        assert view == "/commentLink/edit"
        assert model.commentLinkInstance != null
        assert model.commentLinkInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/commentLink/list'

        response.reset()

        populateValidParams(params)
        def commentLink = new CommentLink(params)

        assert commentLink.save() != null
        assert CommentLink.count() == 1

        params.id = commentLink.id

        controller.delete()

        assert CommentLink.count() == 0
        assert CommentLink.get(commentLink.id) == null
        assert response.redirectedUrl == '/commentLink/list'
    }
}
