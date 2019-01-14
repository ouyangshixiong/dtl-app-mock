/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreAuthRecordDetailComponent } from 'app/entities/store-auth-record/store-auth-record-detail.component';
import { StoreAuthRecord } from 'app/shared/model/store-auth-record.model';

describe('Component Tests', () => {
    describe('StoreAuthRecord Management Detail Component', () => {
        let comp: StoreAuthRecordDetailComponent;
        let fixture: ComponentFixture<StoreAuthRecordDetailComponent>;
        const route = ({ data: of({ storeAuthRecord: new StoreAuthRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreAuthRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StoreAuthRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StoreAuthRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.storeAuthRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
