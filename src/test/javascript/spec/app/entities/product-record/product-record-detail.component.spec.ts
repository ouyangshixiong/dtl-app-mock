/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { ProductRecordDetailComponent } from 'app/entities/product-record/product-record-detail.component';
import { ProductRecord } from 'app/shared/model/product-record.model';

describe('Component Tests', () => {
    describe('ProductRecord Management Detail Component', () => {
        let comp: ProductRecordDetailComponent;
        let fixture: ComponentFixture<ProductRecordDetailComponent>;
        const route = ({ data: of({ productRecord: new ProductRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [ProductRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProductRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.productRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
