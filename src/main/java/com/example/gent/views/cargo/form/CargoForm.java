package com.example.gent.views.cargo.form;

import com.example.gent.views.funcionario.form.FuncionarioForm;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CargoForm extends FormLayout {

    TextField info = new TextField("Cargo");

    Button save = new Button("Salvar");
    Button inativar = new Button("Inativar");
    Button close = new Button("Cancelar");

    public CargoForm(){
        addClassName("cargo-form");
        add(info);
    }

    private HorizontalLayout createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        inativar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

       // save.addClickListener(event -> validateAndSave());
       // inativar.addClickListener(event -> fireEvent(new FuncionarioForm.InativarEvent(this,binderFuncionario.getBean())));
       // close.addClickListener(event -> fireEvent(new FuncionarioForm.CloseEvent(this)));

       // binderFuncionario.addStatusChangeListener(binder -> save.setEnabled(binderFuncionario.isValid()));
        return new HorizontalLayout(save,inativar,close);


    }

}
