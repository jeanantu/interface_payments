package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractServices {

	private OnlinePaymentService onlinePaymentService;
	
	public ContractServices() {
	}
	
	public ContractServices(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int parcelas) {
		double basicQuota = contract.getTotalValue() / parcelas;
        for (int i = 1; i <= parcelas; i++) {
        	// trabalhar com LocalDate rotina incrementar months
            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interest = onlinePaymentService.interest(basicQuota, i);
            double fee = onlinePaymentService.paymentFee(basicQuota + interest);
            double quota = basicQuota + interest + fee;
            contract.getInstallments().add(new Installment(dueDate, quota));
            
        }
	}
}
