# programming-tasks-app

  Online judge service designed for checking programming tasks solved by user.
  There are 2    types of task: standard task and task on time.
  Each answer is run on Docker, so we don't have to be afraid of potential damages
  caused by user's code, another advantage of this solution is that we can set the same
  constraints on RAM and CPU. Solution can be submitted in many different languages corresponding
  with Dockerfile that instructs Docker to install required dependencies (java, gcc, python).
  Once a submission is checked user receives a message via websocket channel.  
  (Java 8, Spring Boot, Spring Data JPA, MySQL, JUnit, Spring Security, JSON Web Token, Docker) 
 
