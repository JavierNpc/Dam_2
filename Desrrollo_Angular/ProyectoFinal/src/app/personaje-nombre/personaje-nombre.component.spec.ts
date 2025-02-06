import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonajeNombreComponent } from './personaje-nombre.component';

describe('PersonajeNombreComponent', () => {
  let component: PersonajeNombreComponent;
  let fixture: ComponentFixture<PersonajeNombreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonajeNombreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonajeNombreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
