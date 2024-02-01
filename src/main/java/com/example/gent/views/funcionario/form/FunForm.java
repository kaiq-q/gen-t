package com.example.gent.views.funcionario.form;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import jakarta.ws.rs.core.Form;

import java.util.ArrayList;
import java.util.List;

@PageTitle("My View")
@Uses(Icon.class)
public class FunForm extends Composite<VerticalLayout> {




    public FunForm() {

        initializeComponents();


    }

    private void initializeComponents(){

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        createHeader();
        createBody();
        creatFooter();



    }

    private void createHeader(){

        //Layout
        HorizontalLayout headerLayout = new HorizontalLayout();

        //Component text
        H3 textCaption = new H3("Funcionário");
        textCaption.setWidth("max-content");

        //Component divisor
        Hr divisor = new Hr();

        //Configure
        headerLayout.setWidthFull();
        getContent().setFlexGrow(1.0, headerLayout);
        headerLayout.addClassName("form-header");
        headerLayout.setWidth("100%");
        headerLayout.getStyle().set("flex-grow", "1");
        headerLayout.setHeight("min-content");
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        headerLayout.setAlignSelf(FlexComponent.Alignment.CENTER, textCaption);

        //Add components to bodyLayout
        headerLayout.add(textCaption);
        getContent().add(headerLayout, divisor);

    }

    private void createBody(){

        FormLayout topBodyLayout = new FormLayout();
        FormLayout midBodyLayout = new FormLayout();

        //Component fields
        TextField  nome      = new TextField("Nome");
        nome.setWidth("min-content");

        TextField  sobrenome = new TextField("Sobrenome");
        sobrenome.setWidth("min-content");

        DatePicker dataNascimento = new DatePicker("Data de nascimento");
        dataNascimento.setWidth("min-content");

        TextField  cpf = new TextField("CPF");
        cpf.setWidth("min-content");

        TextField  rg = new TextField("RG");
        rg.setWidth("min-content");

        ComboBox   cargo = new ComboBox("Cargo");
        cargo.setWidth("min-content");

        TextField cep = new TextField("CEP");
        cep.setWidth("min-content");

        TextField logradouro = new TextField("Logradouro");
        logradouro.setWidth("min-content");

        TextField bairro = new TextField("Bairro");
        bairro.setWidth("min-content");

        TextField cidade = new TextField("Cidade");
        cidade.setWidth("min-content");

        ComboBox  estado = new ComboBox<>("Estado");
        estado.setWidth("min-content");

        TextField complemento = new TextField("Complemento");
        complemento.setWidth("min-content");

        //Component divisor
        Hr divisor = new Hr();


        //Configure
        topBodyLayout.setWidth("100%");
        topBodyLayout.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));

        midBodyLayout.setWidth("100%");
        midBodyLayout.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));


        fillComboCargo(cargo);
        fillComboEstado(estado);


        topBodyLayout.add(nome, sobrenome, dataNascimento, cpf, rg, cargo);
        midBodyLayout.add(cep, logradouro, bairro, cidade, estado, complemento);
        getContent().add(topBodyLayout, divisor, midBodyLayout);
    }

    private void creatFooter() {

        HorizontalLayout footerLayout = new HorizontalLayout();

        Hr divisor = new Hr();

        //Component button
        Button save = new Button("Salvar");
        save.setWidth("min-content");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button delete = new Button("Deletar");
        delete.setWidth("min-content");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button cancel = new Button("Cancelar");
        cancel.setWidth("min-content");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        //Configure
        footerLayout.setWidthFull();
        getContent().setFlexGrow(1.0, footerLayout);

        footerLayout.addClassName("footer-layout");
        footerLayout.setWidth("100%");
        footerLayout.setHeight("min-content");
        footerLayout.setAlignItems(Alignment.CENTER);
        footerLayout.setJustifyContentMode(JustifyContentMode.END);

        footerLayout.setAlignSelf(FlexComponent.Alignment.CENTER, save);
        footerLayout.setAlignSelf(FlexComponent.Alignment.CENTER, delete);
        footerLayout.setAlignSelf(FlexComponent.Alignment.CENTER, cancel);

        footerLayout.add(save, cancel);
        getContent().add(divisor, footerLayout);
    }


    //I will delete it in the next future, now it's only to see how it works.
    record SampleItem(String value, String label, Boolean disable){
    }

    private void fillComboCargo(ComboBox cargoComboBox){
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("analista", "Analista",null));
        sampleItems.add(new SampleItem("engenheiro", "Engenheiro",null));
        sampleItems.add(new SampleItem("programador", "Programador",Boolean.TRUE));
        cargoComboBox.setItems(sampleItems);
        cargoComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());

    }

    record Estado(String estado){
    }

    private void fillComboEstado(ComboBox estadoComboBox){
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado("SP"));
        estados.add(new Estado("MG"));
        estados.add(new Estado("RJ"));
        estados.add(new Estado("ES"));
        estados.add(new Estado("BA"));
        estados.add(new Estado("TO"));
        estados.add(new Estado("SE"));
        estados.add(new Estado("PA"));
        estados.add(new Estado("AL"));
        estados.add(new Estado("PE"));
        estados.add(new Estado("RN"));

        estadoComboBox.setItems(estados);
        estadoComboBox.setItemLabelGenerator(estado -> estado.toString());
    }
}









