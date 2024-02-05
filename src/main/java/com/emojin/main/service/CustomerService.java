package com.emojin.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emojin.main.model.Cart;
import com.emojin.main.model.Customer;
import com.emojin.main.model.Gallery;
import com.emojin.main.model.Program;
import com.emojin.main.model.Ticket;
import com.emojin.main.repository.CartRepository;
import com.emojin.main.repository.CustomerRepository;
import com.emojin.main.repository.GalleryRepository;
import com.emojin.main.repository.ProgramRepository;
import com.emojin.main.repository.TicketRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    private ProgramRepository pRepo;
    @Autowired
    private TicketRepository tRepo;
    
    @Autowired
    private CartRepository cRepo;
    @Autowired
    GalleryRepository gRepo;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    //programfindbyid
    public Program getObject(Long id) {
        return pRepo.findById(id).orElse(null);
    }
    
    //getTicketbyprogid
    public List<Ticket> getTicketbyProgramId(Long id) {
    	List<Ticket> listticket= tRepo.findByProgId(id);
    	return listticket;
    	
    }
    
    //getCartbyCustomerid
    public List<Cart> getCartbyCustomerId(Long id) {
    	List<Cart> listcart= cRepo.findByCustomerId(id);
    	return listcart;  	
    }
    public void saveCart(Cart cart) {
        cRepo.save(cart);
    }
    
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    
    //ticket save
    public void saveTicket(Ticket ticket) {
    	tRepo.save(ticket);
    }
    
    //program save
    public void saveProgram(Program program) {
    	pRepo.save(program);
    }
    
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    public List<Program> getAllProgram(){
		List<Program> list = pRepo.findAll();
		return list;
	}
    public Customer login(String email, String password) {
		Customer customer = customerRepository.findByEmailAndPassword(email, password);
		return customer;
	}

	public boolean findByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		if(customer!=null) return true;
		else return false;
	}

	public List<Gallery> getGallerybyProgramId(Long progId) {
		List<Gallery> listgallery= gRepo.findByProgId(progId);
    	return listgallery;
		
	}
}
