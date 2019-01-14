/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardInfoDetailComponent } from 'app/entities/bankcard-info/bankcard-info-detail.component';
import { BankcardInfo } from 'app/shared/model/bankcard-info.model';

describe('Component Tests', () => {
    describe('BankcardInfo Management Detail Component', () => {
        let comp: BankcardInfoDetailComponent;
        let fixture: ComponentFixture<BankcardInfoDetailComponent>;
        const route = ({ data: of({ bankcardInfo: new BankcardInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BankcardInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BankcardInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bankcardInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
