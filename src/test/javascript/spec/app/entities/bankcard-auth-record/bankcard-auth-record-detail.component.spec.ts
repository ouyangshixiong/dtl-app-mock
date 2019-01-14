/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardAuthRecordDetailComponent } from 'app/entities/bankcard-auth-record/bankcard-auth-record-detail.component';
import { BankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

describe('Component Tests', () => {
    describe('BankcardAuthRecord Management Detail Component', () => {
        let comp: BankcardAuthRecordDetailComponent;
        let fixture: ComponentFixture<BankcardAuthRecordDetailComponent>;
        const route = ({ data: of({ bankcardAuthRecord: new BankcardAuthRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardAuthRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BankcardAuthRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BankcardAuthRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bankcardAuthRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
