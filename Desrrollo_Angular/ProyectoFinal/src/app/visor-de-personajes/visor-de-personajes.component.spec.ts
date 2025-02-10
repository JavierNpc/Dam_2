import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisorDePersonajesComponent } from './visor-de-personajes.component';

describe('VisorDePersonajesComponent', () => {
  let component: VisorDePersonajesComponent;
  let fixture: ComponentFixture<VisorDePersonajesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisorDePersonajesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisorDePersonajesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
