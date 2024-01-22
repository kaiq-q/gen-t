package com.example.gent.views.cliente;

import com.example.gent.entity.Cliente;
import com.example.gent.service.ClienteService;
import com.example.gent.views.MainLayout;
import com.example.gent.views.cliente.form.ClienteForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Clientes")
@Route(value = "cliente", layout = MainLayout.class)
public class ClienteView extends VerticalLayout {

    ClienteForm form;
    Grid<Cliente> grid = new Grid<>(Cliente.class);
    TextField nameField = new TextField();
    ClienteService clienteService;

    public ClienteView(ClienteService clienteService){
        this.clienteService = clienteService;
        addClassName("cliente-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }

    private void inativarCliente(ClienteForm.InativarEvent inativarEvent){
        clienteService.deactiveCliente(inativarEvent.getCliente());
        updateList();
        closeEditor();
    }

    private void saveCliente(ClienteForm.SaveEvent saveEvent){

        if (saveEvent.getCliente().getId() == null){
            clienteService.saveCliente(saveEvent.getCliente());
        }else{
            clienteService.updateCliente(saveEvent.getCliente());
        }
        clienteService.saveCliente(saveEvent.getCliente());
        updateList();
        closeEditor();
    }

    public void addCliente(){
        grid.asSingleSelect().clear();
        editCliente(new Cliente());
    }

    public void closeEditor(){
        form.setCliente(null);
        form.setVisible(false);
        removeClassName("editing-cliente");
    }

    public void editCliente(Cliente cliente){
        if (cliente == null){
            closeEditor();
        }else{
            form.setCliente(cliente);
            form.setVisible(true);
            addClassName("editing-cliente");
        }
    }

    private void updateList(){
        grid.setItems(clienteService.getCliente(nameField.getValue()));
    }

    public HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content-cliente");
        content.setSizeFull();
        return content;
    }

    public void configureForm(){
        form = new ClienteForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCliente);
        form.addInativarListener(this::inativarCliente);
        form.addCloseListener(event -> closeEditor());
    }

    public HorizontalLayout getToolbar(){
        nameField.setPlaceholder("Procurar por nome...");
        nameField.setClearButtonVisible(true);
        nameField.setValueChangeMode(ValueChangeMode.LAZY);
        nameField.addValueChangeListener(event -> updateList());

        Button addClienteButton = new Button("Add cliente");
        addClienteButton.addClickListener(event -> addCliente());

        HorizontalLayout toolbar = new HorizontalLayout(nameField, addClienteButton);
        toolbar.addClassName("toolbar-cliente");
        return toolbar;
    }

    public void configureGrid(){
        grid.addClassName("cliente-grid");
        grid.setSizeFull();
        grid.setColumns("nome","sobrenome","dataNascimento", "cpf", "rg", "ultimoServico", "dataCadastro", "status");
        grid.getColumns().forEach(clienteColumn -> clienteColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editCliente(event.getValue()));

    }
}
