package br.com.vrsoftware.GUI;

import br.com.vrsoftware.domain.Produto;
import br.com.vrsoftware.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroProduto extends JFrame {

    private final ProdutoService produtoService;

    private JTextField descricaoTextField;
    private JTextField precoTextField;
    private JTextField quantidadeTextField;
    private JButton cadastrarButton;

    public CadastroProduto(ProdutoService produtoService) {
        this.produtoService = produtoService;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Cadastrar Produto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel descricaoLabel = new JLabel("Descrição: ");
        descricaoTextField = new JTextField();

        JLabel precoLabel = new JLabel("Preço: ");
        precoTextField = new JTextField();

        JLabel quantidadeLabel = new JLabel("Quantidade: ");
        quantidadeTextField = new JTextField();

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });
        panel.add(descricaoLabel);
        panel.add(descricaoTextField);

        panel.add(precoLabel);
        panel.add(precoTextField);

        panel.add(quantidadeLabel);
        panel.add(quantidadeTextField);

        panel.add(cadastrarButton);

        add(panel);

    }

    private void cadastrarProduto() {
        String descricao = descricaoTextField.getText();
        Double preco = Double.parseDouble(precoTextField.getText());
        int quantidade = Integer.parseInt(quantidadeTextField.getText());

        Produto produto = new Produto(0, descricao, preco, quantidade);
        produtoService.saveProduto(produto);

        JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

        descricaoTextField.setText("");
        precoTextField.setText("");
        quantidadeTextField.setText("");

    }
}
