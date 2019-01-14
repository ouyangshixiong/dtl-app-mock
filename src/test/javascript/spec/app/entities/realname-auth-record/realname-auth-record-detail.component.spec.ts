/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameAuthRecordDetailComponent } from 'app/entities/realname-auth-record/realname-auth-record-detail.component';
import { RealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

describe('Component Tests', () => {
    describe('RealnameAuthRecord Management Detail Component', () => {
        let comp: RealnameAuthRecordDetailComponent;
        let fixture: ComponentFixture<RealnameAuthRecordDetailComponent>;
        const route = ({ data: of({ realnameAuthRecord: new RealnameAuthRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameAuthRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RealnameAuthRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RealnameAuthRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.realnameAuthRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
