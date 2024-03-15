A Social Media Management System
-
This task will help you understand how to manage dependent entities in a one-to-many relationship using JPA, 
with a specific focus on orphan removal for automatic deletion of child entities when they are detached from their 
parent entity. This is a common requirement in applications where the lifecycle of child entities is closely 
tied to their parent entities.


Preconditions
-
This tasks assumes that you've already created SocialMediaDB in the DMS of your choice (MySQL, H2, PostgresQL, etc.).


It must have the following **tables**:


**posts**
- post_id - integer, primary key
- content - text
- author - varchar

**comments**
- comment_id - integer, primary key
- post_id - integer, foreign key posts.post_id
- comment_text - text
- commenter - varchar

_It's advised to populate your database with some test data to verify your code._


Description
-
In this task, you have **four goals**:

1) Define entities for comments and posts tables
2) Implement SocialMediaService
3) Configure JPA via persistence.xml
4) Create a main class which demonstrates the functionality

Defining entities
-
1) You are already provided with classes Comment and Post. Your goal is to make them correct JPA entities using JPA annotations. 
They must be annotated with such annotations as @Table, @Entity, @Column, etc.


2) Configure one-to-many relation between them using @OneToMany and @ManyToOne annotations. 
Also, Post entity should configure orphan removal for comments.


Implementing SocialMediaService
-
This service must provide methods for:

1) creating posts
2) adding a comment to a post
3) removing post with all its comments
4) removing a specific comment from a post

Please take a note that this service uses the functional approach for passing entities which must be created. It means that the service itself is responsible for entity creation and all the necessary fields are set via the passed Consumer.

Configuring persistence.xml
-
You are already provided with the XML file which must be populated with all the required information about:

1) entity classes
2) database connection properties (driver, username, password, etc.)

Main class
-
Create a class with main method and demonstrate how you can:

1) Creating a post
2) Creating multiple comments for this post
3) Deleting one of those comments
4) Deleting the post along with its comments

Requirements
-
1) Comment and Post are correct JPA entities
2) @OneToMany, and @ManyToOne relations are properly configured
3) SocialMediaService is implemented as described
4) The main method is provided
5) All the JPA configuration is done via persistence.xml

Guidelines
-
- Ensure the correct implementation of the one-to-many relationship and orphan removal.
- Add error handling and validations where necessary.
- Include comments explaining the implementation, especially focusing on the orphan removal aspect.
- Test the application to ensure that the deletion of posts also results in the deletion of associated comments, demonstrating the orphan removal functionality.