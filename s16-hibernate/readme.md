Run debug Main and Notice:
0) post-id-1(comment-id-1; comment-id-2); post-id-2(comment-id-3; comment-id-4)
1) after populating table: em.clear()
2) in nPlus1Problem(em)
   1) on em.createNativeQuery("SELECT * FROM Posts", Post.class).getResultList(), Hibernate: SELECT * FROM Posts
   2) on comments1n2 = postId1.getComments(), Hibernate select * comments by post id 1
   3) on comments3n4 = postId2.getComments(), Hibernate select * comments by post id 2
   4) in total we have 3 calls = ammount of posts (2) plus first call 'select all posts'
   5) now go to org.itpu.entity.Post.java and commit line 29 and uncommit line 30: with this FetchType would become Eager
   6) rerun main method
   7) now on posts = em.createNativeQuery("SELECT * FROM Posts", Post.class).getResultList() Hibernate fires 3 calls
3) go to org.itpu.entity.Post.java and uncommit line 30 and uncommit line 29
4) in service.removeComment(2):
   1) hibernate calls select comment by id 2
   2) since we have FetchType.Lazy, when calling comment.getPost(), then hibernate calls select post by id 1
   3) now persistance context has info about Comment-2 and Post-1
   4) on comment.getPost().getComments().remove(comment) we unbind comment relation from post
   5) when em.remove(comment), then hibernate marks comment 'remove' state
   6) when em.getTransaction().commit(), then hibernate calls delete comment by id
5) in service.removePost(2):
   1) em.find(Post.class, 2), then hibernate searched for such an entity, but persistence context remembers only Post-1, and therefore hibernate calls select post by id 2
   2) on em.remove(post), hibernate notice cascate orhans, but they are lazy and not in the context, then hibernate select all comments by post id 2, to get the to persistent context. Now when post and its orphans in the context they are marked 'remove' state
   3) on em.getTransaction().commit() hibernate calls delete comment by id 3 and by id 4 and delete post by id 2
6) now we have in database only post-id-1 and its comment-id-1
7) in checkLazyLoading(EntityManager em)
   1) on em.clear() clean context
   2) on post = em.find(Post.class, 1) hibernate calls select post by id 1 and this post stays in context
   3) on next postCopy = em.find(Post.class, 1) hibernate notices that such object already in context, then it returns Post-id-1 from persistance context (no database calls)
   4) on postById = service.getPostById(1), notice again no database calls
   5) on postByIdCopy = service.getPostById(1), notice again no database calls
   6) em.clear() - cleaning up persistance context
   7) postByIdNewCall = service.getPostById(1), since nothing is in context, hibernate calls select post by id
   8) 