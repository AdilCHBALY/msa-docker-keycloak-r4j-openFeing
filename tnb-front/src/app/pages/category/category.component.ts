import { Component } from '@angular/core';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';

import { HlmInputDirective } from '@spartan-ng/ui-input-helm';

import {
  HlmCaptionComponent,
  HlmTableComponent,
  HlmTdComponent,
  HlmThComponent,
  HlmTrowComponent,
} from '@spartan-ng/ui-table-helm';
import { CategoryDataTableComponent } from '../../components/datatable/category-data-table/category-data-table.component';

import {
  BrnDialogContentDirective,
  BrnDialogTriggerDirective,
} from '@spartan-ng/ui-dialog-brain';
import {
  HlmDialogComponent,
  HlmDialogContentComponent,
  HlmDialogDescriptionDirective,
  HlmDialogFooterComponent,
  HlmDialogHeaderComponent,
  HlmDialogTitleDirective,
} from '@spartan-ng/ui-dialog-helm';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    BrnDialogContentDirective,
    BrnDialogTriggerDirective,
    HlmButtonModule,
    HlmCaptionComponent,
    HlmTableComponent,
    HlmTdComponent,
    HlmThComponent,
    HlmTrowComponent,
    CategoryDataTableComponent,
    HlmDialogComponent,
    HlmDialogContentComponent,
    HlmDialogDescriptionDirective,
    HlmDialogFooterComponent,
    HlmDialogHeaderComponent,
    HlmDialogTitleDirective,
    HlmInputDirective,
  ],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css',
})
export class CategoryComponent {
  categoryForm!: FormGroup;
  constructor(
    private _fb: FormBuilder,
    private _categoryService: CategoryService
  ) {
    this.categoryForm = this._fb.group({
      libelle: '',
      description: '',
      taux: 0,
    });
  }

  onSubmit() {
    if (this.categoryForm.valid) {
      const submit = {
        libelle: this.categoryForm.value.libelle,
        description: this.categoryForm.value.description,
        taux: this.categoryForm.value.taux,
      };
      console.log(submit);
      this._categoryService.addCategory(submit).subscribe({
        next: (res) => {
          this.categoryForm.reset();
          window.location.reload();
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
