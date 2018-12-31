package wwj;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class Janela {

	private JFrame frame;
	private ArrayList<Peregrino> p;
	private int[] d;
	private boolean ficheiro = false;
	boolean corre = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 140, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Janela.class.getResource("/paroquia.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFicheiro = new JLabel("");
		lblFicheiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblFicheiro.setForeground(new Color(0, 0, 139));
		lblFicheiro.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		lblFicheiro.setBounds(104, 133, 214, 16);
		frame.getContentPane().add(lblFicheiro);

		JButton btnAdd = new JButton("Adicionar ficheiro peregrinos");
		btnAdd.setForeground(new Color(255, 140, 0));
		btnAdd.setBackground(new Color(70, 130, 180));
		btnAdd.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		btnAdd.setBounds(104, 162, 214, 33);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblFicheiro.setText(chooser.getSelectedFile().getName());
					p = Functions.Load(chooser.getSelectedFile());
					ficheiro = true;
				}

			}
		});

		JSpinner spinnerDias = new JSpinner();
		spinnerDias.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerDias.setBounds(249, 49, 30, 22);
		frame.getContentPane().add(spinnerDias);

		JSpinner spinnerGrupo = new JSpinner();
		spinnerGrupo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerGrupo.setBounds(249, 84, 30, 22);
		frame.getContentPane().add(spinnerGrupo);

		JLabel lblDuraodias = new JLabel("Dura\u00E7\u00E3o (dias)");
		lblDuraodias.setForeground(new Color(70, 130, 180));
		lblDuraodias.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		lblDuraodias.setBounds(104, 51, 107, 19);
		frame.getContentPane().add(lblDuraodias);

		JLabel lblNGrupos = new JLabel("N\u00BA Grupos");
		lblNGrupos.setForeground(new Color(70, 130, 180));
		lblNGrupos.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		lblNGrupos.setBounds(104, 86, 96, 19);
		frame.getContentPane().add(lblNGrupos);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setForeground(new Color(255, 140, 0));
		btnCalcular.setBackground(new Color(70, 130, 180));
		btnCalcular.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		btnCalcular.setBounds(161, 207, 97, 25);
		frame.getContentPane().add(btnCalcular);

		JLabel lblMximoRepeties = new JLabel("M\u00E1ximo repeti\u00E7\u00F5es");
		lblMximoRepeties.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		lblMximoRepeties.setForeground(new Color(70, 130, 180));
		lblMximoRepeties.setBounds(104, 22, 114, 16);
		frame.getContentPane().add(lblMximoRepeties);

		JSpinner spinnerRep = new JSpinner();
		spinnerRep.setForeground(new Color(70, 130, 180));
		spinnerRep.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerRep.setBounds(249, 19, 30, 22);
		frame.getContentPane().add(spinnerRep);
		
		JLabel labelFeedback = new JLabel("");
		labelFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		labelFeedback.setForeground(new Color(0, 0, 139));
		labelFeedback.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		labelFeedback.setBounds(104, 244, 214, 16);
		frame.getContentPane().add(labelFeedback);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 140, 0));
		btnCancelar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(70, 130, 180));
		btnCancelar.setBounds(293, 206, 97, 25);
		btnCancelar.setVisible(false);
		frame.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corre = false;
				btnCancelar.setVisible(false);
				labelFeedback.setText("Cancelado");
				btnCalcular.setEnabled(true);
			}

		});
		

		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corre = true;
				btnCancelar.setVisible(true);
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						labelFeedback.setText("");
						if (!ficheiro) {
							JOptionPane.showMessageDialog(frame, "Tem de adicionar um ficheiro");
						} else {
							btnCalcular.setEnabled(false);
							d = Functions.Divison(p.size(), (int) spinnerGrupo.getValue());
							int i = 0;
							while (corre && i < 5000000) {
								for (int b = 0; b < p.size(); b++) {
									p.get(b).reset();
								}
								System.out.println(i);

								if (Functions.Sort(p, d, (int) spinnerDias.getValue(), i,
										(int) spinnerRep.getValue())) {
									labelFeedback.setText("Resultados gerados!");
									corre = false;
								}
								i++;
							}
							btnCancelar.setVisible(false);
							btnCalcular.setEnabled(true);
						}
					}

				}).start();
				
				System.out.println("terminou");
			}

		});

	}
}
