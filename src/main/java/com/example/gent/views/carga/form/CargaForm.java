package com.example.gent.views.carga.form;

import com.example.gent.entity.Carga;
import com.example.gent.entity.Cargo;
import com.example.gent.entity.Cliente;
import com.example.gent.entity.Funcionario;
import com.example.gent.entity.auxiliary.CargaFuncionario;
import com.example.gent.enums.StatusCarga;
import com.example.gent.service.ClienteService;
import com.example.gent.service.FuncionarioService;
import com.vaadin.componentfactory.addons.inputmask.InputMask;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CargaForm extends FormLayout{


    NumberField cargaValor = new NumberField("Valor");
    DatePicker dataEntrega = new DatePicker("Data entrega");

    TextField enderecoOrigem = new TextField("Origem");
    TextField enderecoDestino = new TextField("Destino");

    ComboBox<CargaFuncionario> funcionarios = new ComboBox<>("Motorista");

    ComboBox<StatusCarga> statusCarga = new ComboBox<>();

    ComboBox<Cliente> cliente = new ComboBox<>("Cliente");

    FuncionarioService funcionarioService;

    ClienteService clienteService;

    Button save = new Button("Salvar");
    Button delete = new Button("Deletar");
    Button close = new Button("Cancelar");

    BeanValidationBinder<Carga> binder = new BeanValidationBinder<>(Carga.class);


    public CargaForm(FuncionarioService funcionarioService, ClienteService clienteService) {
        this.funcionarioService = funcionarioService;
        this.clienteService = clienteService;
        addClassName("carga-form");

        //binder.bindInstanceFields(this);
        cargaBinder();
        customizeFields();
        add(enderecoOrigem, enderecoDestino, cargaValor, dataEntrega, funcionarios, cliente, createButtonsLayout());

    }


    public static abstract class CargaFormEvent extends ComponentEvent<CargaForm>{
        private Carga carga;
        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         *                   side, <code>false</code> otherwise
         */
       protected CargaFormEvent(CargaForm source, Carga fromClient){
           super(source, false);
           this.carga = fromClient;

       }
       public Carga getCarga(){
           return carga;
       }
    }

    public static class SaveEvent extends CargaFormEvent {
        SaveEvent(CargaForm source, Carga carga) {
            super(source, carga);
        }
    }

    public static class DeleteEvent extends CargaFormEvent {
        DeleteEvent(CargaForm source, Carga carga) {
            super(source, carga);
        }
    }

    public static class CloseEvent extends CargaFormEvent {
        CloseEvent(CargaForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener){
        return addListener(DeleteEvent.class,listener);
    }
    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener){
        return addListener(SaveEvent.class,listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener){
        return addListener(CloseEvent.class,listener);
    }

    public void setCarga(Carga carga){
        binder.setBean(carga);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(b -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);


    }

    public void validateAndSave(){
        if(binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void customizeFields(){
        Div brSuffix = new Div();
        brSuffix.setText("R$");
        cargaValor.setPrefixComponent(brSuffix);
    }

    public void cargaBinder(){
        binder.forField(cargaValor).bind("cargaValor");
        binder.forField(dataEntrega).bind("dataEntrega");
        binder.forField(enderecoOrigem).bind("enderecoOrigem");
        binder.forField(enderecoDestino).bind("enderecoDestino");
        binder.forField(funcionarios).bind("funcionarios");
        binder.forField(cliente).bind("cliente");
        binder.forField(statusCarga).bind("statusCarga");
    }

}