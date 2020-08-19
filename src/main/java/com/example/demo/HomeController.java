package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
public class HomeController {
	@Autowired
	UserRepo Repo;
	@Autowired
	TemoRepo tRepo;
	@Autowired
	RandomRepo rRepo;
	@Autowired
	ExpenseRepo eRepo;
	@Autowired
	FullRepo fRepo;
	@Autowired
	ProductRepo pRepo;
	static int balance=0; 
	static int expenses=0;
	@GetMapping("/")
	public String home() {
		return "index";
}
	
	@PostMapping("/index1")
	public String check(Info user,Model model) {
		if((user.getUsername()).equals("virtusa") && (user.getPassword()).equals("123")) {
			model.addAttribute("product",Repo.findAll());
			model.addAttribute("default",Repo.findByStatus("unpaid"));
			model.addAttribute("paid",Repo.findByStatus("paid"));
			return "welcome";
		}else {
			model.addAttribute("message","Username and Password is incorrect");
			return "index";
		}
		
	}
	
	@GetMapping("/welcome")
	public String getTable(Model model){
		model.addAttribute("product",Repo.findAll());
		model.addAttribute("default",Repo.findByStatus("unpaid"));
		model.addAttribute("paid",Repo.findByStatus("paid"));
		return "welcome";
	}
	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable  String id,Model model) {
		Repo.deleteById(id);
		return "/delete";
		
	}
	@PostMapping("/addToDb")
	public String addtoDB(UserInfo user) {
		Repo.save(user);
		return "result";
	}
	@GetMapping("/expense")
	public String goToExpense(Model model) {
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		TempData data=new TempData();
		data.setName("1");
		data.setExpense(t1.getExpense());
		data.setBalance(t1.getBalance());
		model.addAttribute("money",data);
		return "expense";
	}
	@GetMapping("/updateStatus/{name}")
	public String updateUser(@PathVariable String name,Model model) {
		Optional<UserInfo> temp=Repo.findById(name);
		var a=temp.get();
		UserInfo u=new UserInfo();
		u.setDoorNo(a.getDoorNo());
		u.setName(a.getName());
		u.setEmail(a.getEmail());
		u.setStatus("paid");
		Repo.save(u);
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		TempData data=new TempData();
		data.setName("1");
		data.setExpense(t1.getExpense());
		data.setBalance((Integer.parseInt(t1.getBalance())+5000)+"");
		
		tRepo.save(data);
		return "result";
	}
	@GetMapping("/reset")
	public String resetStatus() {
		List<UserInfo> list=(List<UserInfo>) Repo.findAll();
		for(UserInfo a:list) {
			UserInfo u=new UserInfo();
			u.setDoorNo(a.getDoorNo());
			u.setName(a.getName());
			u.setEmail(a.getEmail());
			u.setStatus("unpaid");
			Repo.save(u);
		}
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		TempData data=new TempData();
		data.setName("1");
		data.setExpense("0");
		data.setBalance("0");
		tRepo.save(data);
		return "result";
	}
	@GetMapping("/updateStatusFine/{name}")
	public String updateUserFine(@PathVariable String name,Model model) {
		Optional<UserInfo> temp=Repo.findById(name);
		var a=temp.get();
		UserInfo u=new UserInfo();
		u.setDoorNo(a.getDoorNo());
		u.setName(a.getName());
		u.setEmail(a.getEmail());
		u.setStatus("paid");
		Repo.save(u);
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		TempData data=new TempData();
		data.setName("1");
		data.setExpense(t1.getExpense());
		data.setBalance((Integer.parseInt(t1.getBalance())+5100)+"");
		
		tRepo.save(data);
		return "result";
	}
	@GetMapping("/addExp")
	public String addExp() {
		
		return "addexp";
	}
	@PostMapping("/adding")
	public String adding(Detail exp) {
		Optional<Random> a=rRepo.findById("1");
		var b=a.get();
		Random temp=new Random();
		temp.setId("1");
		temp.setNum(""+(Integer.parseInt(b.getNum())+1));
		ExpenseData e=new ExpenseData();
		e.setNum(b.getNum());
		e.setName(exp.getReason());
		e.setAmount(exp.getAmount());
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		TempData data=new TempData();
		data.setName("1");
		data.setExpense((Integer.parseInt(t1.getExpense())+Integer.parseInt(exp.getAmount()))+"");
		data.setBalance((Integer.parseInt(t1.getBalance())-Integer.parseInt(exp.getAmount()))+"");
		tRepo.save(data);
		eRepo.save(e);
		rRepo.save(temp);
		
		return "result";
	}
	@GetMapping("/addmonth")
	public String addmonth() {
		return "addmonth";
	}
	@PostMapping("/addMon")
	public String add(TempMon mon) {
		FullData f=new FullData();
		Optional<Random> a=rRepo.findById("1");
		var b=a.get();
		Random temp=new Random();
		temp.setId("1");
		temp.setNum(""+(Integer.parseInt(b.getNum())+1));
		Optional<TempData> t=tRepo.findByName("1");
		var t1=t.get();
		f.setId(b.getNum());
		f.setMonth(mon.getMonth());
		f.setYear(mon.getYear());
		f.setBalance(t1.getBalance());
		f.setExpense(t1.getExpense());
		fRepo.save(f);
		
		List<UserInfo> list=(List<UserInfo>) Repo.findAll();
		for(UserInfo a1:list) {
			UserInfo u=new UserInfo();
			u.setDoorNo(a1.getDoorNo());
			u.setName(a1.getName());
			u.setEmail(a1.getEmail());
			u.setStatus("unpaid");
			Repo.save(u);
		}
		TempData data=new TempData();
		data.setName("1");
		data.setExpense("0");
		data.setBalance("0");
		tRepo.save(data);
		
		
		
		return "result";
	}
	
	@GetMapping("/sentMail/{id}")
//	@ResponseBody
	public String mailsent(@PathVariable String id) {
		Optional<UserInfo> temp=Repo.findById(id);
		var a=temp.get();
		try{
			SendGridEmailer.send("test@example.com",a.getEmail().toString(), "Society Management App", "A Kind Remainder for paying your Maintanence amount for this month.If you have paid already ignore this mail." );
		}catch(Exception e) {
			
		}
		return "result";
	}
	
	
	
}