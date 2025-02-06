import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonajeEstadisticasComponent } from './personaje-estadisticas.component';

describe('PersonajeEstadisticasComponent', () => {
  let component: PersonajeEstadisticasComponent;
  let fixture: ComponentFixture<PersonajeEstadisticasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonajeEstadisticasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonajeEstadisticasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
