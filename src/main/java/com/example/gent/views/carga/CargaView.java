package com.example.gent.views.carga;

import com.example.gent.entity.Carga;
import com.example.gent.service.CargaService;

import com.example.gent.views.MainLayout;
import com.example.gent.views.carga.form.CargaForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cargas")
@Route(value = "carga", layout = MainLayout.class)
public class CargaView extends VerticalLayout {

    CargaForm form;
    Grid<Carga> grid = new Grid<>(Carga.class);
    TextField filterText = new TextField();

    CargaService cargaService;

    public CargaView(CargaService cargaService) {
        this.cargaService = cargaService;
        addClassName("carga-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }

    private void deleteCarga(CargaForm.DeleteEvent deleteEvent){
        cargaService.deleteCarga(deleteEvent.getCarga().getCardaId());
        updateList();
        closeEditor();
    }
    private void saveCarga(CargaForm.SaveEvent saveEvent){
        cargaService.saveCarga(saveEvent.getCarga());
        updateList();
        closeEditor();
    }

    public void addCarga(){
        grid.asSingleSelect().clear();
        editCarga(new Carga());
    }

    public void closeEditor(){
        form.setCarga(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    public void editCarga(Carga carga){
        if (carga == null){
            closeEditor();
        }else{
            form.setCarga(carga);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    private void updateList() {
        grid.setItems(cargaService.getCargas(filterText.getValue()));
    }

    public HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content-carga");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new CargaForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCarga);
        form.addDeleteListener(this::deleteCarga);
        form.addCloseListener(event -> closeEditor());


    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtrar cidade...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addCargaButton = new Button("Add carga");
        addCargaButton.addClickListener(event -> addCarga());

        var toolbar = new HorizontalLayout(filterText, addCargaButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");


        grid.setSizeFull();
        grid.setColumns("cargaDestino", "cargaOrigem", "cargaValor", "dataEntrega");
        //grid.addColumn(carga -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(carga -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editCarga(event.getValue()));

    }
}
