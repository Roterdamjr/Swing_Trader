package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utilitarios.Utilitario;
import dao.LinhaDao;

public class FramePrincipalAnalise extends JFrame {

	private JPanel contentPane;
	private final JTextField txtArquivo = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipalAnalise frame = new FramePrincipalAnalise();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipalAnalise() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Arquivo");
		panel.add(lblNewLabel);
		txtArquivo.setText("C:\\Users\\Roterdam.hp\\Documents\\GitHub\\Eclipse_Projects\\Swing_Trader\\doc");
		panel.add(txtArquivo);
		txtArquivo.setColumns(25);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					importarDadosHistoricos();
					//new LinhaDao().importarArquivo(txtArquivo.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnImportar);

		//TESTE
		//txtArquivo.setText(Utilitario.pathCorrente+"COTAHIST_A2018.TXT");
		//txtArquivo.setText(Utilitario.pathCorrente+"COTAHIST_A2018 - compacto.txt");
		
		
	}
	
	private void importarDadosHistoricos(){
		try {
			new LinhaDao().importarArquivo(txtArquivo.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
