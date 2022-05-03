
package acesso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import dao.*;

public class TesteCrud {

       public static void main(String[] args) throws IOException, SQLException {
	
		Scanner in = new Scanner(System.in);
		boolean continuar = true;
		
		CompaniaDAO dao = new CompaniaDAO();
		
		while (continuar) {
			
			System.out.println("\n\nBem vindo!!! Selecione a opcao desejada");
			System.out.println("1. Inserir dado no banco");
			System.out.println("2. Ver cadastros");
			System.out.println("3. Buscar 1 cadastro");
			System.out.println("4. Remover cadastro");
			System.out.println("5. SAIR");
			System.out.print("\tOpção: ");
			int opcao = in.nextInt();
			
			switch (opcao) {
			
                        //insert    
			case 1:
				System.out.print("Digite o ID: ");
				String id = in.next();
				in.nextLine();
				System.out.print("Digite o nome: ");
				String name = in.nextLine();
				System.out.print("Digite o Dominio: ");
				String dominio = in.nextLine();
				System.out.print("Digite o ano: ");
				String ano = in.nextLine();
				System.out.print("Digite a industria: ");
				String industria = in.nextLine();
				System.out.print("Digite o tamanho: ");
				String tamanho = in.nextLine();
				System.out.print("Digite o localizacao: ");
				String localizacao = in.nextLine();
				System.out.print("Digite o pais: ");
				String pais = in.nextLine();
				System.out.print("Digite o linkedin: ");
				String linkedin = in.nextLine();
				System.out.print("Digite o empregados_atual: ");
				int empregado_atual = in.nextInt();
				System.out.print("Digite o empregados_total: ");
				int empregados_total = in.nextInt();
				Compania p = new Compania();
				p.setId(id);
				p.setNome(name);
				p.setDominio(dominio);
				p.setAno(ano);
				p.setIndustria(industria);
				p.setTamanho(tamanho);
				p.setLocalizacao(localizacao);
				p.setPais(pais);
				p.setLinkedin(linkedin);
				p.setEmpregados_atual(empregado_atual);
				p.setEmpregados_total(empregados_total);
				dao.insert(p);
                                System.out.println("INSERIDO COM SUCESSO!");
				break;
			
                        //mostra todos os registros        
			case 2:
				System.out.println("\nEssa é a atual lista de cadastros:");
				List<Compania> companias = dao.getAll();
				for (Compania pe : companias) {
					System.out.println("\t" 
                                                + " id: "+ pe.getId()
                                                + " nome: "+ pe.getNome() 
                                                + " dominio: "+ pe.getDominio()
                                                + " ano fundação: "+ pe.getAno()
                                                + " industria: "+ pe.getIndustria()
                                                + " tamanho: "+ pe.getTamanho()
                                                + " localidade: "+ pe.getLocalizacao()
                                                + " pais: "+ pe.getPais()
                                                + " linkedin: "+ pe.getLinkedin()
                                                + " empregados atuais: "+ pe.getEmpregados_atual()
                                                + " empregados totais: "+ pe.getEmpregados_total()
                                                );
				}
				break;
			
                        //procura e atualiza	
			case 3: 
				System.out.println("Informe o Nome: ");
				String buscaNome = in.next();
				Compania pBusca = dao.getByName(buscaNome);
				in.nextLine();
				if (pBusca == null) {
					System.out.println("Dado nao encontrado!");
				} else {
					System.out.print("Dado encontrado:\n\t" + pBusca.getNome() + "\nDeseja atualizar (s/n)? ");
					
					if (in.nextLine().toLowerCase().charAt(0) == 's') {
						System.out.print("Digite o novo nome: ");
						String novoName = in.nextLine();
						pBusca.setNome(novoName);
						dao.update(pBusca, buscaNome);
                                                System.out.println("Atualizado com sucesso!");
					}
				}
				break;
			//delete	
			case 4:
				System.out.println("Informe o nome: ");
				String nomeRemocao = in.next();
				dao.delete(nomeRemocao);
				System.out.println("Excluido com sucesso!");
                                break;
			//sair
			case 5:
				continuar = false;
				break;
				
			default:
				System.out.println("Opcao invalida");
			}
			
		}
		
		System.out.println("Obrigado por utilizar este sistema!");
		in.close();
	}


}
