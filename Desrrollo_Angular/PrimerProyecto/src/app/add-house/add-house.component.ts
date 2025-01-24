import {Component, inject, Input} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HousingLocation} from '../housinglocation';
import {RouterModule} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-add-house',
  imports: [CommonModule,RouterModule,ReactiveFormsModule],
  template:`
   <article>
      <section class="listing-apply">
        <h2 class="section-heading">CREATE NEW HOUSE</h2>
        <form [formGroup]="applyForm" (ngSubmit)="submitApplication()">

          <label for="name">Name</label>
          <input id="name" type="text" formControlName="name" />

          <label for="city">City</label>
          <input id="city" type="text" formControlName="city" />

          <label for="state">State</label>
          <input id="state" type="text" formControlName="state" />

          <label for="photo">Photo</label>
          <input id="photo" type="text" formControlName="photo" />

          <label for="availableUnits">Available Units</label>
          <input id="availableUnits" type="number" formControlName="availableUnits" />

          <label for="wifi"> Wifi</label>
          <input id="wifi" type="checkbox" formControlName="wifi" />

          <label for="laundry">Laundry</label>
          <input id="laundry" type="checkbox" formControlName="laundry" />

          <button type="submit" class="primary">Crear</button>
        </form>
      </section>
    </article>

  `,
  styleUrl: './add-house.component.css'
})
export class AddHouseComponent {
    housingService = inject(HousingService);
    housingLocation: HousingLocation | undefined;
    applyForm = new FormGroup({
      name:  new FormControl(''),
      city:  new FormControl(''),
      state:  new FormControl(''),
      photo:  new FormControl(''),
      availableUnits:  new FormControl(),
      wifi:  new FormControl(),
      laundry:  new FormControl()
  
    });
  
    maxId = this.housingService.getAllHousingLocations().length > 0
    ? Math.max(...this.housingService.getAllHousingLocations().map(location => location.id)) :0 ;

    newId = this.maxId +1;
    submitApplication() {
      console.log("Hey funciono", this.applyForm.value.availableUnits);

      this.housingService.addHouse(
        this.newId,
        this.applyForm.value.name ?? '',
        this.applyForm.value.city ?? '',
        this.applyForm.value.state ?? '',
        this.applyForm.value.photo ?? '',
        this.applyForm.value.availableUnits ?? '',
        this.applyForm.value.wifi ?? '',
        this.applyForm.value.laundry ?? ''
      );
    }
    
}
