package com.example.gent.views.funcionario.form;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("My View")
@Route(value = "my-view")
@Uses(Icon.class)
public class FunForm extends Composite<VerticalLayout> {

    public FunForm() {

         createControls();

    }

    public void createControls(){

        H3 h3 = new H3("Cadastro de Funcionários");
        h3.setWidth("max-content");

        Hr hr = new Hr();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h32 = new H3();
        FormLayout formLayout3Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        Hr hr2 = new Hr();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        H4 h4 = new H4();
        FormLayout formLayout3Col2 = new FormLayout();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        TextField textField9 = new TextField();
        TextField textField10 = new TextField();
        Hr hr3 = new Hr();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Button buttonPrimary3 = new Button();

        getContent().setSpacing(false);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);



        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        h32.setText("Informações pessoais");
        h32.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField.setLabel("Nome");
        textField.setWidth("min-content");
        textField2.setLabel("Sobrenome");
        textField2.setWidth("min-content");
        datePicker.setLabel("Data de nascimento");
        datePicker.setWidth("min-content");
        textField3.setLabel("CPF");
        textField3.setWidth("min-content");
        textField4.setLabel("RG");
        textField4.setWidth("min-content");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        h4.setText("Endereço");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, h4);
        h4.setWidth("max-content");
        formLayout3Col2.setWidth("100%");
        formLayout3Col2.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField5.setLabel("CEP");
        textField5.setWidth("min-content");
        textField6.setLabel("Logradouro");
        textField6.setWidth("min-content");
        textField7.setLabel("Bairro");
        textField7.setWidth("min-content");
        textField8.setLabel("Cidade");
        textField8.setWidth("min-content");
        textField9.setLabel("Estado");
        textField9.setWidth("min-content");
        textField10.setLabel("Complemento");
        textField10.setWidth("min-content");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.XLARGE);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutRow3.setAlignItems(Alignment.CENTER);
        layoutRow3.setJustifyContentMode(JustifyContentMode.END);
        buttonPrimary.setText("Button");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Button");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary2);
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary3.setText("Button");
        layoutRow3.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary3);
        buttonPrimary3.setWidth("min-content");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h3);
        getContent().add(hr);
        getContent().add(layoutRow);
        layoutRow.add(h32);
        getContent().add(formLayout3Col);
        formLayout3Col.add(textField);
        formLayout3Col.add(textField2);
        formLayout3Col.add(datePicker);
        formLayout3Col.add(textField3);
        formLayout3Col.add(textField4);
        getContent().add(hr2);
        getContent().add(layoutRow2);
        layoutRow2.add(h4);
        getContent().add(formLayout3Col2);
        formLayout3Col2.add(textField5);
        formLayout3Col2.add(textField6);
        formLayout3Col2.add(textField7);
        formLayout3Col2.add(textField8);
        formLayout3Col2.add(textField9);
        formLayout3Col2.add(textField10);
        getContent().add(hr3);
        getContent().add(layoutRow3);
        layoutRow3.add(buttonPrimary);
        layoutRow3.add(buttonPrimary2);
        layoutRow3.add(buttonPrimary3);

    }
}