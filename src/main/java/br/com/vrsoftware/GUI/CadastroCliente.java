package br.com.vrsoftware.GUI;

import br.com.vrsoftware.domain.Cliente;
import br.com.vrsoftware.service.ClienteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCliente extends JFrame {

    private final ClienteService clienteService;

    private JTextField nomeTextField;

    private JButton cadastrarButton;

    public CadastroCliente(ClienteService clienteService){
        this.clienteService = clienteService;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Cadastrar Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nomeLabel = new JLabel("Nome: ");
        nomeTextField = new JTextField();

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeTextField);

        panel.add(cadastrarButton);

        add(panel);

    }

    private void cadastrarCliente() {
        java.lang.String nome = nomeTextField.getText();

        Cliente cliente = new Cliente(nome);
        clienteService.saveCliente(cliente);

        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

        nomeTextField.setText("");

    }

}
