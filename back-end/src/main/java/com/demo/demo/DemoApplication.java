package com.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.demo.entity.*;
import com.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
//import org.json.simple.*;
//import org.json.simple.parser.JSONParser;
//import org.json.*;
import com.google.gson.*;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoApplication {

	@Autowired
	public UserSpringRepository UserRepository;

	@Autowired
	public UserInfoSpringRepository UserInfoRepository;

	@Autowired
	public ProjectSpringRepository ProjectRepository;

	@Autowired
	public BidsSpringRepository BidRepository;

	public JsonObject convert(String str){
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(str).getAsJsonObject();
		return obj;
	}

	@PostMapping(path="/add")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> signup(@RequestBody String body){

		JsonObject obj = convert(body);
		String mail = obj.get("username").getAsString();
		String password = obj.get("password").getAsString();
		String uname = obj.get("uname").getAsString();
		
		if(mail!=null && mail.trim().length()>0 && password!=null && password.trim().length()>0 && uname!=null && uname.trim().length()>0){
		UserRepository.save(new User(mail, password, uname));
		return new ResponseEntity(HttpStatus.OK);
		}
		else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path="/check")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> signin(@RequestBody String body){

		System.out.println(body);
		JsonObject obj = convert(body);
		String mail = obj.get("username").getAsString();
		String password = obj.get("password").getAsString();
		if(mail!=null && mail.trim().length()>0 && password!=null && password.trim().length()>0){
			User user = UserRepository.findByEmail(mail);
			if(user.password.equals(password)){
				return new ResponseEntity(HttpStatus.OK);
				//return "Hello World";
			}
			else{
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
				//return "Failed";
			}
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
		//return "Failed";
	}

	@PostMapping(path="/enter")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> enterDetails(@RequestBody String body){
		JsonObject obj = convert(body);

		String name = obj.get("name").getAsString();
		String email = obj.get("email").getAsString();
		String phone = obj.get("phone").getAsString();
		String about = obj.get("about").getAsString();
		String skill = obj.get("skill").getAsString();

		if(name!=null && name.trim().length()>0 && email!=null && email.trim().length()>0
		 && phone!=null && phone.trim().length()>0 && about!=null && about.trim().length()>0
		 && skill!=null && skill.trim().length()>0) {
			
			UserInfoRepository.save(new UserInfo(name, email, phone, about, skill));
			return new ResponseEntity(HttpStatus.OK);

		 }
		 else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		 }
	}

	@PostMapping(path="/getdetails")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getProfile(@RequestBody String body){
		JsonObject obj = convert(body);

		String email = obj.get("username").getAsString();

		UserInfo profile = UserInfoRepository.findByEmail(email);
		if(profile != null){
			return new ResponseEntity<UserInfo>(profile, HttpStatus.OK);
		}
		else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path="/postproject")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> postProject(@RequestBody String body){
		JsonObject obj = convert(body);

		String title = obj.get("title").getAsString();
		String description = obj.get("description").getAsString();
		String skills = obj.get("skills").getAsString();
		String budget = obj.get("budget").getAsString();
		String user = obj.get("user").getAsString();

		if(title!=null && title.trim().length()>0 &&
		   description!=null && description.trim().length()>0 &&
		   skills!=null && skills.trim().length()>0 &&
		   budget!=null && budget.trim().length()>0){
			   ProjectRepository.save(new Project(title, description, skills, budget, user));
			   return new ResponseEntity(HttpStatus.OK);
		   }
		   else{
			   return new ResponseEntity(HttpStatus.BAD_REQUEST);
		   }
	}

	@GetMapping(path="/getproject")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getProjects(){

		 List<Project> list = ProjectRepository.findAll();

		 int size = list.size();

		 Project arr[] = new Project[size];

		for(int i=0; i<size; i++){
			arr[i] = list.get(i);
		}

		return new ResponseEntity<Project[]>(arr ,HttpStatus.OK);
	}

	@GetMapping(path="/projects/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getProject(@PathVariable("id") String id){
		//System.out.println(id);
		
		Optional<Project> pro = ProjectRepository.findById(id);

		if(pro!=null){
			return new ResponseEntity<Optional<Project>>(pro, HttpStatus.OK);
		}
		else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path="/getuserprojects")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getUserProjects(@RequestBody String body){

		JsonObject obj = convert(body);
		String user = obj.get("username").getAsString();

		List<Project> userprojects = ProjectRepository.findByUser(user);

		if(userprojects!=null){
			return new ResponseEntity<List<Project>>(userprojects, HttpStatus.OK);
		}
		else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path="/bid")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> postBid(@RequestBody String body){

		JsonObject obj = convert(body);

		String project_id = obj.get("project_id").getAsString();
		String period = obj.get("period").getAsString();
		String bid = obj.get("bid").getAsString();
		String project_title = obj.get("project_title").getAsString();
		String bid_by = obj.get("bid_by").getAsString();

		Bids bidt = BidRepository.findByBidbyAndProjectid(bid_by, project_id);
		//System.out.println(bidt);

		if(bidt == null){
		if(project_id!=null && project_id.trim().length()>0 &&
			period!=null && period.trim().length()>0 &&
			bid!=null && bid.trim().length()>0 &&
			project_title!=null && project_title.trim().length()>0 &&
			bid_by!=null && bid_by.trim().length()>0){
				BidRepository.save(new Bids(project_title, project_id, bid, period, bid_by));
				return new ResponseEntity(HttpStatus.OK);
			}
			else{
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}
		else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}


	@PostMapping(path="/getuserbids")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getBids(@RequestBody String body){

		JsonObject obj = convert(body);

		String bidby = obj.get("username").getAsString();

		List<Bids> bids = BidRepository.findByBidby(bidby);


		return new ResponseEntity<List<Bids>>(bids, HttpStatus.OK);
	}

	@GetMapping(path="/getprojectbids/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> getProjectBids(@PathVariable("id") String id){

		List<Bids> bids = BidRepository.findByProjectid(id);

		System.out.println(bids);

		return new ResponseEntity<List<Bids>>(bids, HttpStatus.OK);

	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
