import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonajeHabilidadesComponent } from './personaje-habilidades.component';

describe('PersonajeHabilidadesComponent', () => {
  let component: PersonajeHabilidadesComponent;
  let fixture: ComponentFixture<PersonajeHabilidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonajeHabilidadesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonajeHabilidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
