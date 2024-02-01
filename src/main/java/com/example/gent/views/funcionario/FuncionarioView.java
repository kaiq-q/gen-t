package com.example.gent.views.funcionario;

import com.example.gent.entity.Funcionario;
import com.example.gent.service.FuncionarioService;
import com.example.gent.views.MainLayout;
import com.example.gent.views.funcionario.form.FuncionarioForm;
import com.example.gent.views.funcionario.form.FunForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Funcionários")
@Route(value = "funcionario", layout = MainLayout.class)
public class FuncionarioView extends VerticalLayout {

    //form
    FuncionarioForm form;
    Grid<Funcionario> grid = new Grid<>(Funcionario.class);

    TextField nameField = new TextField();
    FuncionarioService funcionarioService;

    public FuncionarioView(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        addClassNames("funcionario-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();


    }

    private void saveFuncionario(FuncionarioForm.SaveEvent saveEvent){

        if (saveEvent.getFuncionario().getId() == null){
            funcionarioService.saveFuncionario(saveEvent.getFuncionario());
        }else{
            funcionarioService.updateFuncionatio(saveEvent.getFuncionario());
        }
        updateList();
        closeEditor();
    }

    public void addFuncionario(){
        grid.asSingleSelect().clear();
        editFuncionario(new Funcionario());
    }

    public void closeEditor(){
        form.setFuncionario(null);
        form.setVisible(false);
        removeClassName("editing-funcionario");
    }

    public void editFuncionario(Funcionario funcionario){
        if (funcionario == null){
            closeEditor();
        }else{
            form.setFuncionario(funcionario);
            form.setVisible(true);
            addClassName("editing-funcionario");
        }
    }

    private void inativarFuncionario(FuncionarioForm.InativarEvent inativarEvent){
        funcionarioService.deactiveFuncionario(inativarEvent.getFuncionario());
        updateList();
        closeEditor();
    }

    private void updateList(){
        grid.setItems(funcionarioService.getFuncionario(nameField.getValue()));
    }

    public HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content-funcionario");
        content.setSizeFull();
        return content;
    }

    public void configureForm(){
        form = new FuncionarioForm(funcionarioService.findAllCargos());
        form.setWidth("25em");

        form.addSaveListener(this::saveFuncionario);
        form.addInativarListener(this::inativarFuncionario);
        form.addCloseListener(event -> closeEditor());
    }

    public HorizontalLayout getToolbar(){
        nameField.setPlaceholder("Procurar por nome...");
        nameField.setClearButtonVisible(true);
        nameField.setValueChangeMode(ValueChangeMode.LAZY);
        nameField.addValueChangeListener(event -> updateList());

        Button openFormButton = new Button("New form");
        openFormButton.addClickListener(event -> openCustomForm());

        Button addClienteButton = new Button("Add funcionário");
        addClienteButton.addClickListener(event -> addFuncionario());

        HorizontalLayout toolbar = new HorizontalLayout(nameField, addClienteButton, openFormButton);
        toolbar.addClassName("toolbar-funcionario");
        return toolbar;
    }

    public void configureGrid(){
        grid.addClassName("funcionario-grid");
        grid.setSizeFull();
        grid.setColumns("nome","sobrenome");
        grid.addColumn(funcionario -> funcionario.getCargo().getInfo()).setHeader("Cargo");
        grid.addColumns("dataNascimento", "cpf", "rg", "dataAdmissao", "dataDesligamento", "status");

        grid.getColumns().forEach(funcionarioColumn -> funcionarioColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editFuncionario(event.getValue()));
    }


    private void openCustomForm(){
        FunForm testform = new FunForm();

        Dialog dialog = new Dialog();
        dialog.add(testform);

        dialog.open();

    }
}
