package com.example.gent.views.cargo.form;

import com.example.gent.entity.Cargo;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;

public class CargoForm extends FormLayout {

    TextField info = new TextField("Cargo");

    Button save = new Button("Salvar");
    Button inativar = new Button("Inativar");
    Button close = new Button("Cancelar");

    BeanValidationBinder<Cargo> binderCargo = new BeanValidationBinder<>(Cargo.class);

    public CargoForm(){
        addClassName("cargo-form");
        binderCargo.bindInstanceFields(this);
        add(info, createButtonLayout());
    }

    public static abstract class CargoFormEvent extends ComponentEvent<CargoForm>{
        private Cargo cargo;
        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         *                   side, <code>false</code> otherwise
         */
        public CargoFormEvent(CargoForm source, Cargo fromClient) {
            super(source, false);
            this.cargo = fromClient;
        }

       public Cargo getCargo(){
            return cargo;
       }
    }

    public static class SaveEvent extends CargoFormEvent{
        public SaveEvent(CargoForm source, Cargo cargo) {
            super(source, cargo);
        }
    }

    public static class InativarEvent extends CargoFormEvent {

        public InativarEvent(CargoForm source, Cargo cargo) {
            super(source, cargo);
        }
    }

    public static class CloseEvent extends CargoFormEvent{

        public CloseEvent(CargoForm source) {
            super(source, null);
        }
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener){
        return addListener(CloseEvent.class,listener);
    }
    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener){
        return addListener(SaveEvent.class,listener);
    }
    public Registration addInativarListener(ComponentEventListener<InativarEvent> listener){
        return addListener(InativarEvent.class,listener);
    }


    public void setCargo(Cargo cargo){
        binderCargo.setBean(cargo);
    }

    private HorizontalLayout createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        inativar.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

       save.addClickListener(event -> validateAndSave());
       inativar.addClickListener(event -> fireEvent(new InativarEvent(this,binderCargo.getBean())));
       close.addClickListener(event -> fireEvent(new CloseEvent(this)));

       binderCargo.addStatusChangeListener(binder -> save.setEnabled(binderCargo.isValid()));
       return new HorizontalLayout(save,inativar,close);


    }

    public void validateAndSave(){
        if (binderCargo.isValid()){
            fireEvent(new SaveEvent(this, binderCargo.getBean()));
        }
    }

}
