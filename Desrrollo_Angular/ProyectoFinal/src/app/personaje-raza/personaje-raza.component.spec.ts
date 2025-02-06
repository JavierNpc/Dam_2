import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonajeRazaComponent } from './personaje-raza.component';

describe('PersonajeRazaComponent', () => {
  let component: PersonajeRazaComponent;
  let fixture: ComponentFixture<PersonajeRazaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonajeRazaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonajeRazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
