import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';
import { BrnMenuTriggerDirective } from '@spartan-ng/ui-menu-brain';
import { HlmMenuModule } from '@spartan-ng/ui-menu-helm';
import { BrnTableModule } from '@spartan-ng/ui-table-brain';
import { HlmTableModule } from '@spartan-ng/ui-table-helm';
import { HlmScrollAreaComponent } from '@spartan-ng/ui-scrollarea-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemIconDirective,
  HlmMenuItemSubIndicatorComponent,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuShortcutComponent,
  HlmSubMenuComponent,
} from '@spartan-ng/ui-menu-helm';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../../services/category.service';
import { formatCreatedAt } from '../../../../../utlis/utli';
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
import { TauxService } from '../../../services/taux.service';
@Component({
  selector: 'app-category-data-table',
  standalone: true,
  imports: [
    BrnDialogContentDirective,
    BrnDialogTriggerDirective,
    HlmDialogComponent,
    HlmDialogContentComponent,
    HlmDialogDescriptionDirective,
    HlmDialogFooterComponent,
    HlmDialogHeaderComponent,
    HlmDialogTitleDirective,
    FormsModule,
    BrnMenuTriggerDirective,
    HlmMenuModule,
    BrnTableModule,
    HlmTableModule,
    HlmButtonModule,
    HlmScrollAreaComponent,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmMenuItemIconDirective,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuLabelComponent,
    HlmMenuSeparatorComponent,
    HlmMenuShortcutComponent,
    HlmSubMenuComponent,
    CommonModule,
    HlmInputDirective,
    ReactiveFormsModule,
  ],
  templateUrl: './category-data-table.component.html',
  styleUrl: './category-data-table.component.css',
})
export class CategoryDataTableComponent implements OnInit {
  categoryForm!: FormGroup;
  rateForm!: FormGroup;
  CATEGORY_DATA: any[] = [];
  constructor(
    private _categoryService: CategoryService,
    private _tauxService: TauxService,
    private _fb: FormBuilder,
    private _fb2: FormBuilder
  ) {
    this.categoryForm = this._fb.group({
      libelle: '',
      description: '',
    });
    this.rateForm = this._fb2.group({
      taux: 0,
    });
  }

  ngOnInit(): void {
    this.getCategories();
  }

  onEditCategory(id: number) {
    const data = {
      ...this.categoryForm.value,
      taux: 0,
    };
    console.log(data);
    this._categoryService.updateCategory(id, data).subscribe({
      next: (res) => {
        this.categoryForm.reset();
        this.getCategories();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onAddTaux(id: number) {
    const data = {
      value: this.rateForm.value.taux,
      category_id: id,
    };
    console.log(data);
    this._tauxService.addCategory(data).subscribe({
      next: (res) => {
        this.rateForm.reset();
        this.getCategories();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onDelete(id: number) {
    this._categoryService.deleteCategory(id).subscribe({
      next: (res) => {
        this.categoryForm.reset();
        this.getCategories();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getCategories() {
    this._categoryService.getAllCategories().subscribe({
      next: (res) => {
        this.CATEGORY_DATA = res.map((category: any) => {
          return {
            ...category,
            createdAt: formatCreatedAt(category.createdAt),
          };
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
