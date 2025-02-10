import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaDePersonajesComponent } from './lista-de-personajes.component';

describe('ListaDePersonajesComponent', () => {
  let component: ListaDePersonajesComponent;
  let fixture: ComponentFixture<ListaDePersonajesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaDePersonajesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaDePersonajesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
