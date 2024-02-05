package com.emojin.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emojin.main.model.Cart;
import com.emojin.main.model.Customer;
import com.emojin.main.model.EventOrganizer;
import com.emojin.main.model.Gallery;
import com.emojin.main.model.Program;
import com.emojin.main.model.ReviewsAndComplaints;
import com.emojin.main.model.Stadium;
import com.emojin.main.model.Ticket;
import com.emojin.main.model.Vendor;
import com.emojin.main.repository.CartRepository;
import com.emojin.main.repository.EventOrganizerRepository;
import com.emojin.main.repository.GalleryRepository;
import com.emojin.main.repository.ProgramRepository;
import com.emojin.main.repository.ReviewsAndComplaintsRepository;
import com.emojin.main.repository.StadiumRepository;
import com.emojin.main.repository.VendorRepository;
import com.emojin.main.service.CustomerService;
import com.emojin.main.service.EventOrganizerService;
import com.emojin.main.service.VendorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	private CustomerService cService;
	
	@Autowired
	Program program;
	@Autowired
	ProgramRepository prRepo;
	@Autowired
	Customer customer;
	@Autowired
	EventOrganizerRepository orgRepo;
	@Autowired
	VendorRepository vRepo;
	@Autowired
	private EventOrganizerService orgService;
	@Autowired
	private VendorService vService;
	@Autowired
	StadiumRepository stRepo;
	@Autowired
	CartRepository cRepo;
	@Autowired
	ReviewsAndComplaintsRepository rRepo;
	@Autowired
	GalleryRepository gRepo;
	
	//Opening home page
	@GetMapping("/")
	public String home(Model m,HttpSession session) {
		List<Program> programs = cService.getAllProgram();
		m.addAttribute("Programs", programs);
		return "index.html";
	}
	
	
	//second page
		@GetMapping("/booking")
		public String bookingCheck(@RequestParam("progId") long progId, Model m, HttpSession session) {
			
			program = cService.getObject(progId);
			customer=(Customer) session.getAttribute("user");
			
			/*
			 * List<MovieDetails> movie2 = cService.getAllMovie(); List<String> checkMovie =
			 * new ArrayList<>(); for (MovieDetails string : movie2) {
			 * checkMovie.add(string.getMovieName()); }
			 */
			if (customer!=null && program!=null) {
				List<Ticket> ticket = cService.getTicketbyProgramId(program.getProgId());
				List<String> tname = new ArrayList<String>();

				for (Ticket t : ticket) {
						tname.add(t.getSeatNo());

				}
				m.addAttribute("program", program);
				m.addAttribute("tname", tname);
				return "ticketPurchase.html";

			} else {
				return "redirect:/loginForm";

			}

		}

		//seat booking process
		@PostMapping("/book-seat")
		public String bookSeat(@RequestParam("seatNo[]") List<String> SeatNo,HttpSession session,RedirectAttributes r ) {
			
			
			List<Ticket> ct=customer.getTickets();
			List<Ticket> pt=program.getTickets();
			List<Ticket> ticketincart = new ArrayList<Ticket>();
			double totalPrice = 0;
			int totalTicket = 0;
			for(String s:SeatNo) {
				Ticket ticket=new Ticket(s,500,program.getDate(),"After 2 days",customer,program,null,null);
				ct.add(ticket);
				pt.add(ticket);
				ticketincart.add(ticket);
				totalPrice +=ticket.getPrice();
				totalTicket +=1;
				cService.saveTicket(ticket);
			}
			customer.setTickets(ct);
			program.setTickets(pt);
			Cart cart = new Cart("Unpaid",totalPrice,totalTicket,ticketincart,null,customer,program,SeatNo);
			cService.saveCart(cart);
			cService.saveCustomer(customer);
			cService.saveProgram(program);
			program=null;
			
			return "done.html";
		}
		
		
		//Cart
		@GetMapping("/order-history")
		public String history(HttpSession session,Model m) {
			Long cid = customer.getCustomerId();
			List<Cart> carts=cService.getCartbyCustomerId(cid);
			m.addAttribute("carts", carts);
			return "cart.html";
		}
	//Registration User
		@GetMapping("/register")
		public String register(Model m) {
			
			m.addAttribute("menu", "register");
			return "CustomerRegister.html";
		}
//		Login form
		@GetMapping("/loginForm")
		public String loginForm(Model m) {
			m.addAttribute("menu", "login");
			return "customerLogin.html";
		}
		
	//User save process
	@PostMapping("/save")
	public String save(@ModelAttribute("customer") Customer customer,RedirectAttributes r) {
		boolean x=cService.findByEmail(customer.getEmail());
		if(x){r.addFlashAttribute("nsuccess","This email is associated with an account!!Try with another mail");}
		else {
		cService.saveCustomer(customer);
		r.addFlashAttribute("success","Registered Successfully!!");}
		return "redirect:/register";
		
	}
	
//	Login process
	@PostMapping("/processing")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model m) {

			Customer customer = cService.login(email, password);

			if (customer == null) {
				m.addAttribute("failed", "Invalied login");
				return "customerLogin.html";
			} else {
				session.setAttribute("user", customer);
			}
			return "redirect:/home";
		
	}
	
	@GetMapping("/home")
	public String mainDashboard(HttpSession session, Model m) {
		

		String message = (String) session.getAttribute("msg");
		m.addAttribute("message", message);
		session.removeAttribute("msg");
//		System.out.println(message);
		List<Program> programs = cService.getAllProgram();
		m.addAttribute("Programs", programs);

		return "main_dashboard.html";
	}
	
//	User Setting
	@GetMapping("/setting")
	public String getSetting(Model m, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("user");
		m.addAttribute("user", customer);
		m.addAttribute("menu", "setting");
		return "setting.html";
	}
	
//	User update form
	@GetMapping("/setting/update/{id}")
	public String updateForm(@PathVariable("id") long id, Model m,HttpSession s) {
		m.addAttribute("menu", "setting");
		Customer customer = (Customer) s.getAttribute("user");
		m.addAttribute("user", customer);
		return "updateprofile.html";

	}
	
//	update Details
	@PostMapping("/setting/update-details")
	public String updateDetails(@ModelAttribute("customer") Customer cust, HttpSession session) {
		String name = cust.getCustomerName();
		String date = cust.getDateOfBirth();
		String gender = cust.getGender();
		String no = cust.getMobileNo();
		String add = cust.getAddress();
		String mail = cust.getEmail();
		String pass = cust.getPassword();
		Customer customer = (Customer) session.getAttribute("user");
		customer.setCustomerName(name);
		customer.setDateOfBirth(date);
		customer.setGender(gender);
		customer.setMobileNo(no);
		customer.setAddress(add);
		customer.setEmail(mail);
		customer.setPassword(pass);
		cService.saveCustomer(customer);

		return "redirect:/setting";

	}
	
//	Logout process
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("organizer");
		session.removeAttribute("vendor");
		customer=null;

		return "redirect:/";
	}
	
	//Login for event organizer
	@GetMapping("/eventOrg_LoginForm")
	public String eventOrg_LoginForm(Model m) {
		m.addAttribute("menu", "login");
		return "EventOrganizer_Login.html";
	}

	// Registration for event organizer
		@GetMapping("/organizer/register")
		public String orgRegister(Model m) {

			m.addAttribute("menu", "register");
			return "EventOrg_Register.html";
		}

//	 	Event organizer save process
		@PostMapping("/organizer/save")
		public String orgSave(@ModelAttribute("organizer") EventOrganizer organizer,RedirectAttributes r) {
			 boolean x=orgService.findByEmail(organizer.getEmail());
			if(x){r.addFlashAttribute("x","this email exist an account");
			return "redirect:/organizer/register";}
			else {
				orgService.saveEventOrganizer(organizer);
				 r.addFlashAttribute("y","registered successfully");
				return "redirect:/organizer/register";
			}
		}
			
//		 	Login process for event organizer
			@PostMapping("/organizer/processing")
			public String organizerLogin(@RequestParam("email") String email, @RequestParam("password") String password,
					HttpSession session, Model m) {
				EventOrganizer organizer = orgRepo.findByEmailAndPassword(email,password);
				if (organizer == null) {
					m.addAttribute("failed", "Invalied login");
					return "EventOrganizer_Login.html";
				} else {
					session.setAttribute("organizer", organizer);			
					}
					return "redirect:/organizer/home";
				}
			
			@GetMapping("/organizer/home")
			public String mainDashboard_org(HttpSession session, Model m) {
				EventOrganizer organizer = (EventOrganizer) session.getAttribute("organizer");
				m.addAttribute("organizer", organizer);
				
				return "main_dashboard_org.html";
			}
			
			// Event organizer Setting
			@GetMapping("/organizer/setting")
			public String getSetting_org(Model m, HttpSession session) {
				EventOrganizer organizer = (EventOrganizer) session.getAttribute("organizer");
				if (organizer != null) {
					m.addAttribute("user", organizer);
					return "setting_org.html";
				} else {
					return "redirect:/eventOrg_LoginForm";
				}
			}
			
//			event organizer update form
			@GetMapping("/organizer/setting/update/{id}")
			public String updateForm_org(@PathVariable("id") long id, Model m) {
				EventOrganizer organizer = orgService.getEventOrganizerById(id);
				System.out.println(id);
				m.addAttribute("menu", "setting");
				m.addAttribute("organizer", organizer);
				return "update_details_eventOrg";

			}
			
//		 	Save the updated event organizer info
			@PostMapping("/organizer_update")
			public String updatedinfo_organizer(@ModelAttribute("organizer") EventOrganizer organizer, Model model,
					HttpSession session) {

				// Retrieve the existing event organizer from the database
				EventOrganizer existingOrganizer = orgService.getEventOrganizerById(organizer.getOrganizerId());

				existingOrganizer.setOrganizationName(organizer.getOrganizationName());
				existingOrganizer.setDesignation(organizer.getDesignation());
				existingOrganizer.setDepartment(organizer.getDepartment());
				existingOrganizer.setMobileNo(organizer.getMobileNo());
				existingOrganizer.setAddress(organizer.getAddress());
				existingOrganizer.setPassword(organizer.getPassword());

				orgService.saveEventOrganizer(existingOrganizer);

				session.setAttribute("organizer", existingOrganizer);

				return "redirect:/organizer/setting"; // Redirect to the updated profile page
			}
			
//			set programs
			@PostMapping("/set_program")
			public String setProgram(@RequestParam String eventTitle,@RequestParam String duration,@RequestParam String date,@RequestParam String image,@RequestParam Long stdId, HttpSession session, Model model) {
			    EventOrganizer organizer = (EventOrganizer) session.getAttribute("organizer");
			    Stadium stadium = orgService.findStadium(stdId);
			    Program program = new Program(eventTitle,duration,date,image,organizer,stadium,null,null);
			    prRepo.save(program);
			    model.addAttribute("yes","Program Launched Successfully!!!");
			    
			    return "redirect:/organizer/home";
			}

			@GetMapping("/organizer/programs")
			public String myprograms(HttpSession session,Model m) {
				EventOrganizer org=(EventOrganizer) session.getAttribute("organizer");
				List<Program> programs= prRepo.findByOrganizerId(org.getOrganizerId());
				m.addAttribute("programs", programs);
				return "OrganizerPrograms";
			}
			// Registration for Vendor
			@GetMapping("/vendor/register")
			public String vendorRegister(Model m) {
				return "Vendor_Register.html";
			}
			// Vendor save process
			@PostMapping("/vendor/save")
			public String vendorSave(@ModelAttribute("vendor") Vendor vendor,RedirectAttributes r) {
				 Vendor v=vRepo.findByEmail(vendor.getEmail());
					if(v!=null){r.addFlashAttribute("x","this email exist an account");
					return "redirect:/vendor/register";}
					else {
						vRepo.save(vendor);
						 r.addFlashAttribute("y","registered successfully");
						return "redirect:/vendor/register";
					}
	
			}

			// Login for Vendor
			@GetMapping("/vendor/loginForm")
			public String vendorLoginForm(Model m) {
				return "Vendor_Login.html";
			}

			// Login process for Vendor
			@PostMapping("/vendor/processing")
			public String vendorLogin(@RequestParam("email") String email, @RequestParam("password") String password,
					HttpSession session, Model m) {
				Vendor vendor = vRepo.findByEmailAndPassword(email,password);
				if (vendor == null) {
					m.addAttribute("failed", "Invalied login");
					return "Vendor_Login.html";
				} else {
					session.setAttribute("vendor", vendor);	
					}
				m.addAttribute("Programs", program);
					return "redirect:/vendor/home";
			}
			
			@GetMapping("/vendor/home")
			public String vendorhome(HttpSession session, Model m) {
				

				List<Program> programs = cService.getAllProgram();
				m.addAttribute("Programs", programs);

				return "main_dashboard_vendor.html";
			}
			
			
		//  Vendor  Setting
			@GetMapping("/vendor/setting")
			public String getSetting_vendor(Model m, HttpSession session) {
				Vendor vendor = (Vendor) session.getAttribute("vendor");
				Long id = vendor.getVendorId();
				List<Gallery> gallerys=gRepo.findGalleryByVendorId(id);
				m.addAttribute("gallerys",gallerys);
				m.addAttribute("user", vendor);
				return "setting_vendor.html";
			}

//		 	Vendor update form
			@GetMapping("/vendor/setting/update/{id}")
			public String updateForm_vendor(@PathVariable("id") long id, Model m) {
				Vendor vendor = vRepo.findByVendorId(id);
				m.addAttribute("vendor", vendor);
				return "update_details_vendor.html";
			}

//			 Save the updated vendor
			@PostMapping("/vendor_update")
			public String updatedinfo_vendor(@ModelAttribute("vendor") Vendor vendor, Model model, HttpSession session) {

				// Retrieve the existing vendor from the database
				Vendor v = vRepo.findByVendorId(vendor.getVendorId());

				v.setVendorName(vendor.getVendorName());
				v.setCompanyName(vendor.getCompanyName());
				v.setMobileNo(vendor.getMobileNo());
				v.setAddress(vendor.getAddress());
				v.setPassword(vendor.getPassword());

				vRepo.save(v);

				session.setAttribute("vendor", v);

				return "redirect:/vendor/setting";

			}
			
			@PostMapping("/pay")
			public String pay(Model m) {
				Long cid = customer.getCustomerId();
				List<Cart> carts=cService.getCartbyCustomerId(cid);
				for(Cart c:carts) {
					c.setStatus("Paid");
					cRepo.save(c);
				}
				
				return "redirect:/order-history";
			}
			
			@PostMapping("/reviewpost")
			public String reviewpost(@RequestParam String name,@RequestParam String email,@RequestParam String description) {
				
				ReviewsAndComplaints review = new ReviewsAndComplaints(name,email,null,description,customer);
				rRepo.save(review);
				return "redirect:/home";
			}
			
			@GetMapping("/vendor/booking-gallery")
			public String bookingGallery(@RequestParam("progId") long progId, Model m, HttpSession session) {
				
				program = cService.getObject(progId);
				
				
				if(program!=null) {
					List<Gallery> gallery = cService.getGallerybyProgramId(program.getProgId());
					List<String> gname = new ArrayList<String>();

					for (Gallery g : gallery) {
							gname.add(g.getgName());

					}
					m.addAttribute("program", program);
					m.addAttribute("tname", gname);
					return "vendorbookgallery.html";}else {
						return "redirect:/vendor/home";
					}

			
			}
			
			@PostMapping("/vendor/book-gallery")
			public String bookgallery(@RequestParam("seatNo[]") List<String> SeatNo,HttpSession session,RedirectAttributes r ) {
				
				Vendor vendor = (Vendor) session.getAttribute("vendor");
				List<Gallery> vg=vendor.getGallery();
				List<Gallery> pg=program.getGallery();
				List<Gallery> gallerycart = new ArrayList<Gallery>();
				double totalPrice = 0;
				int totalGallery = 0;
				for(String s:SeatNo) {
					Gallery gallery=new Gallery(s,program.getStadium().getStdLocation(),50,vendor,program.getStadium(),null,program);
					vg.add(gallery);
					pg.add(gallery);
					gallerycart.add(gallery);
					totalPrice +=50000;
					totalGallery +=1;
					gRepo.save(gallery);
				}
				vendor.setGallery(vg);
				program.setGallery(pg);
				vRepo.save(vendor);
				cService.saveProgram(program);
				program=null;
				
				return "VendorDone.html";
			}
			

}
