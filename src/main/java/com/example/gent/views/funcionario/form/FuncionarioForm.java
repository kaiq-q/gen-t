package com.example.gent.views.funcionario.form;

import com.example.gent.entity.Cargo;
import com.example.gent.entity.Funcionario;
import com.example.gent.service.CargoService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class FuncionarioForm extends FormLayout {

    TextField nome = new TextField("Nome");
    TextField sobrenome = new TextField("Sobrenome");
    ComboBox<Cargo> cargo = new ComboBox<>("Cargo");
    DatePicker dataNascimento = new DatePicker("Data nascimento");
    TextField cpf = new TextField("C.P.F");
    TextField rg = new TextField("R.G");
    TextField endereco = new TextField("Endereço");
    TextField cidade = new TextField("Cidade");
    TextField estado = new TextField("Estato");
    Button save = new Button("Salvar");
    Button inativar = new Button("Inativar");
    Button close = new Button("Cancelar");

    BeanValidationBinder<Funcionario> binderFuncionario = new BeanValidationBinder<>(Funcionario.class);

    public FuncionarioForm(List<Cargo> cargoList){
        addClassName("funcionario-form");
        customizeFields();
        cargo.setItems(cargoList);
        cargo.setItemLabelGenerator(Cargo::getInfo);
        binderFuncionario.bindInstanceFields(this);
        add(nome,sobrenome,cargo,dataNascimento,cpf,rg,endereco,cidade,estado,createButtonLayout());
    }

    public static abstract class FuncionarioFormEvent extends ComponentEvent<FuncionarioForm>{

        private Funcionario funcionario;
        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         *                   side, <code>false</code> otherwise
         */
        public FuncionarioFormEvent(FuncionarioForm source, Funcionario fromClient) {
            super(source, false);
            this.funcionario = fromClient;
        }

        public Funcionario getFuncionario(){
            return funcionario;
        }
    }

    public static class SaveEvent extends FuncionarioForm.FuncionarioFormEvent {
        SaveEvent(FuncionarioForm source, Funcionario funcionario){
            super(source,funcionario);
        }
    }

    public static class InativarEvent extends FuncionarioForm.FuncionarioFormEvent {
        InativarEvent(FuncionarioForm source, Funcionario funcionario){
            super(source, funcionario);
        }
    }

    public static class CloseEvent extends FuncionarioForm.FuncionarioFormEvent {
        CloseEvent(FuncionarioForm source){
            super(source, null);
        }
    }

    public Registration addCloseListener(ComponentEventListener<FuncionarioForm.CloseEvent> listener){
        return addListener(FuncionarioForm.CloseEvent.class,listener);
    }

    public Registration addInativarListener(ComponentEventListener<FuncionarioForm.InativarEvent> listener){
        return addListener(FuncionarioForm.InativarEvent.class,listener);
    }

    public Registration addSaveListener(ComponentEventListener<FuncionarioForm.SaveEvent>listener){
        return addListener(FuncionarioForm.SaveEvent.class,listener);
    }

    public void setFuncionario(Funcionario funcionario){
        binderFuncionario.setBean(funcionario);
    }

    private HorizontalLayout createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        inativar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        inativar.addClickListener(event -> fireEvent(new FuncionarioForm.InativarEvent(this,binderFuncionario.getBean())));
        close.addClickListener(event -> fireEvent(new FuncionarioForm.CloseEvent(this)));

        binderFuncionario.addStatusChangeListener(binder -> save.setEnabled(binderFuncionario.isValid()));
        return new HorizontalLayout(save,inativar,close);


    }

    public void validateAndSave(){
        if(binderFuncionario.isValid()){
            fireEvent(new SaveEvent(this,binderFuncionario.getBean()));
        }
    }

    public void customizeFields(){
        cpf.setRequiredIndicatorVisible(true);
        cpf.setPattern("^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}$");
        cpf.setAllowedCharPattern("[0-9.-]");
        cpf.setMaxLength(14);
    }
}
