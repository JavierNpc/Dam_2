import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelecccionHabilidadesComponent } from './selecccion-habilidades.component';

describe('SelecccionHabilidadesComponent', () => {
  let component: SelecccionHabilidadesComponent;
  let fixture: ComponentFixture<SelecccionHabilidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelecccionHabilidadesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelecccionHabilidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
