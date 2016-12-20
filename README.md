# appdirecthiring
<h1>Importing The Project into local machine:</h1>
 <p><ol>  
 <li>Import as a gradle project in any of the IDE(prefferably Idea)</li>
   <li>Create database(mysql) to which you want the aaplication to be connected</li>
   <li>Set appropriate db user and password in application.properties file</li>
   <li>Create Sample student in the database(mysql) with id 1;</li>
   <li>Build The Project with your IDE or Terminal</li>
        <ol>
        <li>To build from terminal go to module appdirect-web and run ./gradlew build</li>
        </ol>
   <li>Run Application.java</li>
   </ol>
   </p>
   <h2>Building And Deploying the jar</h2>
   <ol>
    <li>Make sure ,to build passing all the test the connected databse should student with ID 1 else build without test</li>
    <li>go to module appdirect-web and run ./gradlew build or ./gradlew build -x test(in case a Student is not there with Id 1)</li>
    <li>Copy the jar from build inside appdirect-web module to cloudserever whose details are:</li>
    <ol>
      <li>Url: appdirectmerajtest.cloudapp.net</li>
      <li>User and password to this server has been mentioned in the mail</li>
    </ol>
      < li>Once jar is copied login toh the cloud and stop the current runnig jar</li>
      <li>Run the coppied jar:</li>
    <ol>Steps are:
      <li>nohup java -Dspring.datasource.username=<?> -Dspring.datasource.password=<?> -Dlogging.file=<?> -Dconsumer.key=<?> -Dconsumer.secret=<?> -jar appdirect-web-1.0-SNAPSHOT.jar &</li>
        <ol>Importat properties for the app
          <li>
             spring.datasource.username : Db UserName(for the cloud it has been mentioned on the email)
          </li>
          <li>
           spring.datasource.password: Db user password(for the cloud it has been mentioned on the email))
          </li>
          <li>
          logging.file: path ending in the file name  
          </li>
          <li>
          consumer.key: appdirect product integration key  
          </li>
          <li>
          consumer.secret: appdirect product secret
          </li>
      </ol>
    </ol>
   </ol>
   <h3>Testing Api Integration for Appdirect</h3>
   <ol>
     <li>Regiseter the url :<i> http://appdirectmerajtest.cloudapp.net/api/addirectintegration/v1/eventlistner?eventUrl={eventUrl}</i> for the added product
     </li>
     <li>Login to the server and rerun jar passing the new key and secret and run the test</li>
   </ol>
