1. Look at Detector bean lifecycle
2. Change Detector bean scope to prototype and notice assertation
3. Uncomment #configurationVsComponent() and use @Component and later use @Configuration above 
SpringSpecificsMain.class: notice assertion is different.
4. Inspect MusicServiceMain - solution for Music Service (Optional) task:
   Description
   1. Create a MusicService class with a List attribute songs. The class should have an appropriate getter method for its attribute.
   2. Create a Song class with the following attributes: title, artist and year. The class should have appropriate getter and setter methods for its attributes.
   3. Add an init method with a @PostConstruct annotation to the MusicService class that adds 3 songs to the service list.
   4. Add a destroy method with a @PreDestroy annotation to the MusicService class that writes a message to the console that MusicService is shutting down.
   5. Use a Spring configuration class MusicServiceConfig to define and manage the MusicService bean.