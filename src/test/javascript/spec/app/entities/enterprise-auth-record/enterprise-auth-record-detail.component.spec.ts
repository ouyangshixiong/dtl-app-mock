/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseAuthRecordDetailComponent } from 'app/entities/enterprise-auth-record/enterprise-auth-record-detail.component';
import { EnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

describe('Component Tests', () => {
    describe('EnterpriseAuthRecord Management Detail Component', () => {
        let comp: EnterpriseAuthRecordDetailComponent;
        let fixture: ComponentFixture<EnterpriseAuthRecordDetailComponent>;
        const route = ({ data: of({ enterpriseAuthRecord: new EnterpriseAuthRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseAuthRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnterpriseAuthRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnterpriseAuthRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.enterpriseAuthRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
