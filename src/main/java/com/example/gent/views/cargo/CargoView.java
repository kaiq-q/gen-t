package com.example.gent.views.cargo;

import com.example.gent.entity.Cargo;
import com.example.gent.service.CargoService;
import com.example.gent.views.MainLayout;
import com.example.gent.views.cargo.form.CargoForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cargo")
@Route(value = "cargo", layout = MainLayout.class)
public class CargoView extends VerticalLayout {

    CargoForm form;
    Grid<Cargo> grid = new Grid<>(Cargo.class);
    TextField cargoField = new TextField();
    CargoService cargoService;

    public CargoView(CargoService cargoService){
        this.cargoService = cargoService;
        addClassName("cargo-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToobar(),getContent());
        updateList();
        closeEditor();

    }

    private void InativarCargo(CargoForm.InativarEvent inativarEvent){
        cargoService.deactiveCargo(inativarEvent.getCargo());
        updateList();
        closeEditor();
    }

    private void saveCargo(CargoForm.SaveEvent saveEvent){
        cargoService.saveCargo(saveEvent.getCargo());
        updateList();
        closeEditor();
    }

    public void addCargo(){
        grid.asSingleSelect().clear();
        editCargo(new Cargo());
        removeClassName("editing-cargo");
    }

    public void closeEditor(){
        form.setCargo(null);
        form.setVisible(false);
        removeClassName("editing-cargo");
    }

    public void editCargo(Cargo cargo){
        if (cargo == null){
            closeEditor();
        }else{
            form.setCargo(cargo);
            form.setVisible(true);
            addClassName("editing-cargo");
        }

    }

    private void updateList(){
        grid.setItems(cargoService.getCargo(cargoField.getValue()));
    }

    public HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content-cargo");
        content.setSizeFull();
        return content;
    }

    public void configureForm(){
        form = new CargoForm();
        form.setWidth("25em");

        form.addSaveListener(this::saveCargo);
        form.addInativarListener(this::InativarCargo);
        form.addCloseListener(event -> closeEditor());
    }

    public HorizontalLayout getToobar(){
        cargoField.setPlaceholder("Procucar por cargo...");
        cargoField.setClearButtonVisible(true);
        cargoField.setValueChangeMode(ValueChangeMode.LAZY);
        cargoField.addValueChangeListener(event -> updateList());

        Button addCargoButton = new Button("Add cargo");
        addCargoButton.addClickListener(event -> addCargo());

        HorizontalLayout toolbar = new HorizontalLayout(cargoField,addCargoButton);
        toolbar.addClassName("toolbar-cargo");
        return toolbar;
    }

    public void configureGrid(){
        grid.addClassName("cargo-grid");
        grid.setSizeFull();
        grid.setSizeFull();
        grid.setColumns("info","status");

        grid.asSingleSelect().addValueChangeListener(event -> editCargo(event.getValue()));

    }
}
