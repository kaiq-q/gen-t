package com.example.gent.views.cliente.form;

import com.example.gent.entity.Cliente;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;


public class ClienteForm extends FormLayout{

    TextField nome = new TextField("Nome");
    TextField sobrenome = new TextField("Sobrenome");
    DatePicker dataNascimento = new DatePicker("Data nascimento");
    TextField cpf = new TextField("C.P.F");
    TextField rg = new TextField("R.G");
    TextField endereco = new TextField("Endereço");
    TextField cidade = new TextField("Cidade");
    TextField estado = new TextField("Estato");

    Button save = new Button("Salvar");
    Button inativar = new Button("Inativar");
    Button close = new Button("Cancelar");

    BeanValidationBinder<Cliente> binderCliente = new BeanValidationBinder<>(Cliente.class);

    public ClienteForm(){
        addClassName("cliente-form");
        binderCliente.bindInstanceFields(this);
        add(nome,sobrenome,dataNascimento,cpf,rg,endereco,cidade,estado,createButtonLayout());
    }

    public static abstract class ClienteFormEvent extends ComponentEvent<ClienteForm>{

        private Cliente cliente;
        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         *                   side, <code>false</code> otherwise
         */
        public ClienteFormEvent(ClienteForm source, Cliente fromClient) {
            super(source, false);
            this.cliente = fromClient;
        }

        public Cliente getCliente() {return cliente;}
    }

    public static class SaveEvent extends ClienteFormEvent{
        SaveEvent(ClienteForm source, Cliente cliente){
            super(source,cliente);
        }
    }

    public static class InativarEvent extends ClienteFormEvent{
        InativarEvent(ClienteForm source, Cliente cliente){
            super(source, cliente);
        }
    }

    public static class CloseEvent extends ClienteFormEvent{
        CloseEvent(ClienteForm source){
            super(source, null);
        }
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener){
        return addListener(CloseEvent.class,listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent>listener){
        return addListener(SaveEvent.class,listener);
    }

    public Registration addInativarListener(ComponentEventListener<InativarEvent> listener){
        return addListener(InativarEvent.class,listener);
    }

    public void setCliente(Cliente cliente){
        binderCliente.setBean(cliente);
    }

    private HorizontalLayout createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        inativar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        inativar.addClickListener(event -> fireEvent(new InativarEvent(this,binderCliente.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binderCliente.addStatusChangeListener(binder -> save.setEnabled(binderCliente.isValid()));
        return new HorizontalLayout(save,inativar,close);


    }

    public void validateAndSave(){
        if(binderCliente.isValid()){
            fireEvent(new SaveEvent(this,binderCliente.getBean()));
        }
    }
}
