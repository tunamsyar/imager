import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HousingLocation } from '../../housinglocation';

@Component({
  selector: 'app-housing-location',
  standalone: true,
  imports: [CommonModule],
  template: `
      <img class="img-fluid" [src]="housingLocation.photo" alt="Exterior photo of {{housingLocation.name}}">
      <h2>{{ housingLocation.name }}</h2>
      <p>{{ housingLocation.city}}, {{housingLocation.state }}</p>
  `,
  styleUrls: ['./housing-location.component.scss']
})
export class HousingLocationComponent {
  @Input() housingLocation!: HousingLocation;
}
