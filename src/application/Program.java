package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractServices;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero:");
		int numero = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy):");
		LocalDate data = LocalDate.parse(sc.next(),fmt);
		System.out.print("Valor do contrato:");
		double valorContrato = sc.nextDouble();

		Contract obj = new Contract (numero, data, valorContrato);
		
		System.out.print("Entre com o numero de parcelas:");
		Integer numeroParcelas = sc.nextInt();
		
		ContractServices service = new ContractServices(new PaypalService());
		service.processContract(obj, numeroParcelas);
		
		System.out.println("Parcelas:");
		//percorrer a lista buscando cada Installment (parcela)
		for (Installment installment : obj.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();


	}

}
